function createXMLHttpRequest() {
    var request;
    try {
        request = new XMLHttpRequest();
    } catch (e) {
        try {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                alert("Il browser non supporta AJAX");
                return null;
            }
        }
    }
    return request;
}

function loadAjaxDoc(url, method, params, cFunction) {
    var request = createXMLHttpRequest();

    if (request) {
        request.onreadystatechange = function () {
            if (this.readyState === 4) {
                if (this.status === 200) {
                    cFunction(this);
                } else {
                    alert("Errore AJAX: " + this.statusText);
                }
            }
        };

        setTimeout(() => {
            if (request.readyState < 4) {
                request.abort();
            }
        }, 15000);

        if (method.toLowerCase() === "get") {
            request.open("GET", params ? `${url}?${params}` : url, true);
            request.send(null);
        } else {
            request.open("POST", url, true);
            request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            request.send(params);
        }
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const moboInput = document.getElementById('motherboardInput');
    const cpuInput  = document.getElementById('processorInput');
    const ramInput  = document.getElementById('ramInput');
    const ctx       = document.getElementById('ContextPath').value;

    cpuInput.disabled = true;
    ramInput.disabled = true;

    moboInput.addEventListener('input', () => {
        const raw = moboInput.value.trim();
        if (!raw) return;
        const nomeM = raw.split(' -')[0];
        const params = 'nameM=' + encodeURIComponent(nomeM);

        loadAjaxDoc(`${ctx}/FetchProduct`, 'GET', params, function (xhr) {
            let data;
            try {
                data = JSON.parse(xhr.responseText);
            } catch (e) {
                console.error("JSON parse error:", e);
                return;
            }

            const cpus = data.cpus || [];
            const rams = data.rams || [];

            const cpuDatalist = document.getElementById('processoriList');
            cpuDatalist.innerHTML = '';
            cpus.forEach(cpu => {
                const opt = document.createElement('option');
                opt.value = `${cpu.nome} - €${cpu.pdFisico.prezzo}`;
                opt.dataset.id = cpu.codiceIdentificativo;
                cpuDatalist.appendChild(opt);
            });

            const ramDatalist = document.getElementById('ramList');
            ramDatalist.innerHTML = '';
            rams.forEach(ram => {
                const opt = document.createElement('option');
                opt.value = `${ram.nome} - €${ram.pdFisico.prezzo}`;
                opt.dataset.id = ram.codiceIdentificativo;
                ramDatalist.appendChild(opt);
            });

            cpuInput.disabled = false;
            ramInput.disabled = false;
            console.log(`Caricati ${cpus.length} CPU e ${rams.length} RAM compatibili con ${nomeM}`);
        });
    });
});
