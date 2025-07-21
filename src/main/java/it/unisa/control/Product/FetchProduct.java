package it.unisa.control.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.DAO.ComponentiFisici.ProcessoreDao;
import it.unisa.model.DAO.ComponentiFisici.RamDao;
import it.unisa.model.DAO.ComponentiFisici.SchedaMadreDao;
import it.unisa.model.Filters.BuildChecker;
import it.unisa.model.Filters.Processore;
import it.unisa.model.Filters.Ram;
import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.beans.ArticoloCompletoBean;

@WebServlet("/FetchProduct")
public class FetchProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FetchProduct() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rawNameM = request.getParameter("nameM");
        List<ArticoloCompletoBean> artcpu = new ArrayList<>();
        List<ArticoloCompletoBean> artram = new ArrayList<>();

        if (rawNameM != null && !rawNameM.trim().isEmpty()) {
            try {
                SchedaMadreDao moboDao = new SchedaMadreDao();
                ProcessoreDao cpuDao = new ProcessoreDao();
                RamDao ramDao = new RamDao();

                SchedaMadre selectedMobo = moboDao.doRetrieveByKey(rawNameM);
                List<Processore> allCpus = cpuDao.doRetrieveAll("");
                List<Ram> allRams = ramDao.doRetrieveAll("");

                ArticoloCompletoDao articoloDao = new ArticoloCompletoDao();

                for (Processore cpu : allCpus) {
                    if (BuildChecker.isCompatible(cpu, selectedMobo)) {
                        ArticoloCompletoBean bean = articoloDao.doRetrieveByName(cpu.nome());
                        if (bean != null) artcpu.add(bean);
                    }
                }

                for (Ram ram : allRams) {
                    if (BuildChecker.isCompatible(selectedMobo, ram)) {
                        ArticoloCompletoBean bean = articoloDao.doRetrieveByName(ram.nome());
                        if (bean != null) artram.add(bean);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("cpus", artcpu);
        result.put("rams", artram);

        Gson gson = new Gson();
        String json = gson.toJson(result);

        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(json);
            out.flush();
        }
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
