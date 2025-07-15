document.addEventListener('DOMContentLoaded', function() {
  let sidebar   = document.querySelector('.filter-offerte-sidebar');
  let toggleBtn = document.getElementById('toggleFilters');
  let inputsToWatch = [
    'filterCategory',
    'filterBrand',
    'priceRange',
    'exclusiveSwitch',
    'searchInput'
  ];
  
  // 1) Toggle della sidebar (solo mobile)
  if (toggleBtn && sidebar) {
    toggleBtn.addEventListener('click', function() {
      sidebar.classList.toggle('active');
    });
  }

  // 2) Funzione di filtraggio
  function filterProducts() {
    let catInput  = document.getElementById('filterCategory');
    let cat       = catInput ? catInput.value : '';

    let brandInput = document.getElementById('filterBrand');
    let brand      = brandInput ? brandInput.value : '';

    let priceInput = document.getElementById('priceRange');
    let priceLabel = document.getElementById('priceLabel');
    let priceMax   = priceInput ? Number(priceInput.value) : NaN;

    let exclInput  = document.getElementById('exclusiveSwitch');
    let onlyExcl   = exclInput ? exclInput.checked : false;

    let searchInput = document.getElementById('searchInput');
    let query       = searchInput ? searchInput.value.toLowerCase() : '';

    // Aggiorna il label del prezzo
    if (priceLabel && priceInput) {
        priceLabel.textContent = `€0 - €${priceMax}`;
    }

    let items = document.querySelectorAll('.product-item');
    for (let i = 0; i < items.length; i++) { // 'i' è ora con ambito di blocco per il ciclo
      let item        = items[i]; // 'item' è con ambito di blocco dato che abbiamo let
      let itemCat     = item.dataset.category || '';
      let itemBrand   = item.dataset.brand    || '';
      let itemPrice   = Number(item.dataset.price) || 0;
      let itemExcl    = item.dataset.exclusive === 'true';
      let nameElem    = item.querySelector('h4');
      let nameText    = nameElem ? nameElem.textContent.toLowerCase() : '';

      let show = true; // 'show' anche esso lvl blocco
      if (cat && itemCat !== cat) show = false;
      if (brand && itemBrand !== brand) show = false;
      if (!isNaN(priceMax) && itemPrice > priceMax) show = false;
      if (onlyExcl && !itemExcl) show = false;
      if (query && nameText.indexOf(query) === -1) show = false;

      item.style.display = show ? 'block' : 'none';
    }
  }

  // 3) Collego gli handler
  for (let j = 0; j < inputsToWatch.length; j++) { 
    let id = inputsToWatch[j]; 
    let el = document.getElementById(id); 
    if (el) {
      el.addEventListener('input', filterProducts);
    }
  }

  // 4) Filtra subito una prima volta
  filterProducts();

  // 5) Su resize togliamo l'"active" su desktop
  window.addEventListener('resize', function() {
    if (window.innerWidth > 767 && sidebar) {
      sidebar.classList.remove('active');
    }
  });
});