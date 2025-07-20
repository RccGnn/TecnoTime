package it.unisa.control.Admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.DAO.Articoli.ProdottoDigitaleDao;
import it.unisa.model.DAO.Articoli.ProdottoFisicoDao;
import it.unisa.model.DAO.Articoli.ServizioDao;
import it.unisa.model.DAO.ComponentiFisici.AlimentatoreDao;
import it.unisa.model.DAO.ComponentiFisici.CaseDao;
import it.unisa.model.DAO.ComponentiFisici.ProcessoreDao;
import it.unisa.model.DAO.ComponentiFisici.SchedaMadreDao;
import it.unisa.model.DAO.ComponentiFisici.SchedaVideoDAO;
import it.unisa.model.Filters.Alimentatore;
import it.unisa.model.Filters.Case;
import it.unisa.model.Filters.Processore;
import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.Filters.SchedaVideo;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.ImmagineBean;
import it.unisa.model.beans.ProdottoDigitaleBean;
import it.unisa.model.beans.ProdottoFisicoBean;
import it.unisa.model.beans.ServizioBean;

/**
 * Servlet implementation class AdminAggiungiProdotto
 */
@WebServlet("/AdminAggiungiProdotto")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1MB
	    maxFileSize = 1024 * 1024 * 10,  // 10MB
	    maxRequestSize = 1024 * 1024 * 50 // 50MB
	)
public class AdminAggiungiProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAggiungiProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		
		String tipologia = (String) request.getParameter("tipologia");
		Date date = Date.valueOf(LocalDate.now());
		
		
		
		if (tipologia.equals("processore")) {
			String nomecompletocpu = (String) request.getParameter("nomecompleto");
			String marcacpu = (String) request.getParameter("marca");
			String socketcpu = (String) request.getParameter("socket");	
			String wattcpu = request.getParameter("watt");
			String seriale  = (String) request.getParameter("seriale");
			String prezzopf =  request.getParameter("prezzo");
			String descrizionepf = (String) request.getParameter("descrizione");
			String quantitaMagazzino= request.getParameter("quantita");
			String url = (String) request.getParameter("url");
			String datarilascio = request.getParameter("dataRilascio");
			System.out.println(datarilascio);
			
			java.sql.Date dataSql = java.sql.Date.valueOf(datarilascio);
			
			ProdottoFisicoBean pf = new ProdottoFisicoBean();
			ProdottoFisicoDao pfDao = new ProdottoFisicoDao();
			String ci="ART"+UUID.randomUUID().toString().substring(0,10);
			pf.setArticolo_codiceIdentificativo(ci);
			pf.setCategoria(tipologia);
			pf.setCodiceIdentificativo(ci);
			pf.setDataUltimaPromozione(date);
			pf.setDescrizione(descrizionepf);
			pf.setDisponibilita(true);
			pf.setEnteErogatore(marcacpu);
			pf.setNome(nomecompletocpu);
			pf.setPreassemblato(false);
			pf.setPrezzo(Float.parseFloat(prezzopf));
			pf.setSeriale(seriale);
			pf.setQuantitaMagazzino(Integer.parseInt(quantitaMagazzino));

			ArticoloCompletoBean art = new ArticoloCompletoBean();
			ArticoloCompletoDao artdao = new ArticoloCompletoDao();
			ImmagineBean imgs = new ImmagineBean();
			if(url!=null) {
				imgs.setUrl(url);
				imgs.setArticolo_codiceIdentificativo(ci);
				ArrayList<ImmagineBean> img = new ArrayList<ImmagineBean>();
				img.add(imgs);
				art.setPdFisico(pf);
				art.setImmagini(img);
			}
			
			Processore cpu = new Processore(nomecompletocpu, marcacpu, socketcpu, dataSql, Integer.parseInt(wattcpu));
			ProcessoreDao cpuDao = new ProcessoreDao();
			try {
				if(url!=null) {
					artdao.doSave(art);
				}
				pfDao.doSave(pf);
				cpuDao.doSave(cpu);				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}else  if(tipologia.equals("scheda_madre")) {
			String nomecompletomb = (String) request.getParameter("nomecompleto");
			String marcamb = (String) request.getParameter("marca");
			String socketmb = (String) request.getParameter("socket");
			String dimensionemb = (String) request.getParameter("dimensione");
			String PCImb =  request.getParameter("PCI");
			String wattmb =  request.getParameter("watt");
			String supportoRam = (String) request.getParameter("supportoRam");
			String seriale  = (String) request.getParameter("seriale");
			String prezzopf =  request.getParameter("prezzo");
			String descrizionepf = (String) request.getParameter("descrizione");
			String quantitaMagazzino=  request.getParameter("quantitaMagazzino");
			String url = (String) request.getParameter("url");
			
			ProdottoFisicoBean pf = new ProdottoFisicoBean();
			ProdottoFisicoDao pfDao = new ProdottoFisicoDao();
			String ci="ART"+UUID.randomUUID().toString().substring(0,10);
			pf.setArticolo_codiceIdentificativo(ci);
			pf.setCategoria(tipologia);
			pf.setCodiceIdentificativo(ci);
			pf.setDataUltimaPromozione(date);
			pf.setDescrizione(descrizionepf);
			pf.setDisponibilita(true);
			pf.setEnteErogatore(marcamb);
			pf.setNome(nomecompletomb);
			pf.setPreassemblato(false);
			pf.setPrezzo(Float.parseFloat(prezzopf));
			pf.setSeriale(seriale);
			pf.setQuantitaMagazzino(Integer.parseInt(quantitaMagazzino));
			
			ArticoloCompletoBean art = new ArticoloCompletoBean();
			ArticoloCompletoDao artdao = new ArticoloCompletoDao();
			ImmagineBean imgs = new ImmagineBean();
			if(url!=null) {
				imgs.setUrl(url);
				imgs.setArticolo_codiceIdentificativo(ci);
				ArrayList<ImmagineBean> img = new ArrayList<ImmagineBean>();
				img.add(imgs);
				art.setPdFisico(pf);
				art.setImmagini(img);
			}
			SchedaMadre mb = new SchedaMadre(nomecompletomb, marcamb, socketmb, dimensionemb,Float.parseFloat(PCImb), supportoRam,Integer.parseInt(wattmb));
			SchedaMadreDao mbDao = new SchedaMadreDao();
			
			try {
				if(url!=null) {
					artdao.doSave(art);
				}
				pfDao.doSave(pf);
				mbDao.doSave(mb);				
			}catch (SQLException e) {
				e.printStackTrace();
			}

			
		}else  if(tipologia.equals("scheda_video")) {
			String nomecompletogpu = (String) request.getParameter("nomecompleto");
			String marcagpu = (String) request.getParameter("marca");
			String PCIgpu = request.getParameter("PCI");
			String vram=  request.getParameter("vram");
			String tipoRam = (String) request.getParameter("tipoRam");
			String wattgpu= request.getParameter("watt");
			String seriale  = (String) request.getParameter("seriale");
			String prezzopf = request.getParameter("prezzo");
			String descrizionepf = (String) request.getParameter("descrizione");
			String quantitaMagazzino=  request.getParameter("quantitaMagazzino");
			String url = (String) request.getParameter("url");
			
			ProdottoFisicoBean pf = new ProdottoFisicoBean();
			ProdottoFisicoDao pfDao = new ProdottoFisicoDao();
			String ci="ART"+UUID.randomUUID().toString().substring(0,10);
			pf.setArticolo_codiceIdentificativo(ci);
			pf.setCategoria(tipologia);
			pf.setCodiceIdentificativo(ci);
			pf.setDataUltimaPromozione(date);
			pf.setDescrizione(descrizionepf);
			pf.setDisponibilita(true);
			pf.setEnteErogatore(marcagpu);
			pf.setNome(nomecompletogpu);
			pf.setPreassemblato(false);
			pf.setPrezzo(Float.parseFloat(prezzopf));
			pf.setSeriale(seriale);
			pf.setQuantitaMagazzino(Integer.parseInt(quantitaMagazzino));
			

			ArticoloCompletoBean art = new ArticoloCompletoBean();
			ArticoloCompletoDao artdao = new ArticoloCompletoDao();
			ImmagineBean imgs = new ImmagineBean();
			if(url!=null) {
				imgs.setUrl(url);
				imgs.setArticolo_codiceIdentificativo(ci);
				ArrayList<ImmagineBean> img = new ArrayList<ImmagineBean>();
				img.add(imgs);
				art.setPdFisico(pf);
				art.setImmagini(img);
			}
			
			SchedaVideo gpu = new SchedaVideo(nomecompletogpu, marcagpu, Float.parseFloat(PCIgpu), Integer.parseInt(vram), tipoRam, Integer.parseInt(wattgpu));
			SchedaVideoDAO gpuDao = new SchedaVideoDAO(); 
			
			try {
				if(url!=null) {
					artdao.doSave(art);
				}
				pfDao.doSave(pf);
				gpuDao.doSave(gpu);				
			}catch (SQLException e) {
				e.printStackTrace();
			}

		
		}else  if(tipologia.equals("alimentatori")) {
			String nomecompletopsu= (String) request.getParameter("nomecompleto");
			String marcapsu = (String) request.getParameter("marca");
			String wattpsu = request.getParameter("watt");
			String seriale  = (String) request.getParameter("seriale");
			String prezzopf =  request.getParameter("prezzo");
			String descrizionepf = (String) request.getParameter("descrizione");
			String quantitaMagazzino=  request.getParameter("quantitaMagazzino");
			String url = (String) request.getParameter("url");
			
			
			ProdottoFisicoBean pf = new ProdottoFisicoBean();
			ProdottoFisicoDao pfDao = new ProdottoFisicoDao();
			String ci="ART"+UUID.randomUUID().toString().substring(0,10);
			pf.setArticolo_codiceIdentificativo(ci);
			pf.setCategoria(tipologia);
			pf.setCodiceIdentificativo(ci);
			pf.setDataUltimaPromozione(date);
			pf.setDescrizione(descrizionepf);
			pf.setDisponibilita(true);
			pf.setEnteErogatore(marcapsu);
			pf.setNome(nomecompletopsu);
			pf.setPreassemblato(false);
			pf.setPrezzo(Float.parseFloat(prezzopf));
			pf.setSeriale(seriale);
			pf.setQuantitaMagazzino(Integer.parseInt(quantitaMagazzino));
			
			ArticoloCompletoBean art = new ArticoloCompletoBean();
			ArticoloCompletoDao artdao = new ArticoloCompletoDao();
			ImmagineBean imgs = new ImmagineBean();
			if(url!=null) {
				imgs.setUrl(url);
				imgs.setArticolo_codiceIdentificativo(ci);
				ArrayList<ImmagineBean> img = new ArrayList<ImmagineBean>();
				img.add(imgs);
				art.setPdFisico(pf);
				art.setImmagini(img);
			}
			
			Alimentatore psu = new Alimentatore(nomecompletopsu, marcapsu, Integer.parseInt(wattpsu));
			AlimentatoreDao psuDao = new AlimentatoreDao();
			
			try {
				if(url!=null) {
					artdao.doSave(art);
				}
				pfDao.doSave(pf);
				psuDao.doSave(psu);				
			}catch (SQLException e) {
				e.printStackTrace();
			}

		
		
		}else if (tipologia.equals("_case")) {
			String nomecompletocase= (String) request.getParameter("nomecompleto");
			String dimensionecase = (String) request.getParameter("dimensione");
			String marcacase =  (String) request.getParameter("marca");
			String seriale  = (String) request.getParameter("seriale");
			String prezzopf =  request.getParameter("prezzo");
			String descrizionepf = (String) request.getParameter("descrizione");
			String quantitaMagazzino= request.getParameter("quantitaMagazzino");
			String url = (String) request.getParameter("url");
			
			
			ProdottoFisicoBean pf = new ProdottoFisicoBean();
			ProdottoFisicoDao pfDao = new ProdottoFisicoDao();
			String ci="ART"+UUID.randomUUID().toString().substring(0,10);
			pf.setArticolo_codiceIdentificativo(ci);
			pf.setCategoria(tipologia);
			pf.setCodiceIdentificativo(ci);
			pf.setDataUltimaPromozione(date);
			pf.setDescrizione(descrizionepf);
			pf.setDisponibilita(true);
			pf.setEnteErogatore(marcacase);
			pf.setNome(nomecompletocase);
			pf.setPreassemblato(false);
			pf.setPrezzo(Float.parseFloat(prezzopf));
			pf.setSeriale(seriale);
			pf.setQuantitaMagazzino(Integer.parseInt(quantitaMagazzino));
			

			ArticoloCompletoBean art = new ArticoloCompletoBean();
			ArticoloCompletoDao artdao = new ArticoloCompletoDao();
			ImmagineBean imgs = new ImmagineBean();
			if(url!=null) {
				imgs.setUrl(url);
				imgs.setArticolo_codiceIdentificativo(ci);
				ArrayList<ImmagineBean> img = new ArrayList<ImmagineBean>();
				img.add(imgs);
				art.setPdFisico(pf);
				art.setImmagini(img);
			}
			
			
			Case contenitore = new Case(nomecompletocase, dimensionecase);
			CaseDao caseDao = new CaseDao();
		
			try {
				if(url!=null) {
					artdao.doSave(art);
				}
				pfDao.doSave(pf);
				caseDao.doSave(contenitore);				
			}catch (SQLException e) {
				e.printStackTrace();
			}

			
		
		}else if (tipologia.equals("altro")) {
			String nome= (String) request.getParameter("nome");
			String seriale  = (String) request.getParameter("seriale");
			String prezzopf =  request.getParameter("prezzo");
			String marcapf = (String) request.getParameter("marca");
			String descrizionepf = (String) request.getParameter("descrizione");
			String quantitaMagazzino=  request.getParameter("quantitaMagazzino");
			String url = (String) request.getParameter("url");
			
			
			ProdottoFisicoBean pf = new ProdottoFisicoBean();
			ProdottoFisicoDao pfDao = new ProdottoFisicoDao();
			String ci="ART"+UUID.randomUUID().toString().substring(0,10);
			pf.setArticolo_codiceIdentificativo(ci);
			pf.setCategoria(tipologia);
			pf.setCodiceIdentificativo(ci);
			pf.setDataUltimaPromozione(date);
			pf.setDescrizione(descrizionepf);
			pf.setDisponibilita(true);
			pf.setEnteErogatore(marcapf);
			pf.setNome(nome);
			pf.setPreassemblato(false);
			pf.setPrezzo(Float.parseFloat(prezzopf));
			pf.setSeriale(seriale);
			pf.setQuantitaMagazzino(Integer.parseInt(quantitaMagazzino));
			
			
			ArticoloCompletoBean art = new ArticoloCompletoBean();
			ArticoloCompletoDao artdao = new ArticoloCompletoDao();
			ImmagineBean imgs = new ImmagineBean();
			if(url!=null) {
				imgs.setUrl(url);
				imgs.setArticolo_codiceIdentificativo(ci);
				ArrayList<ImmagineBean> img = new ArrayList<ImmagineBean>();
				img.add(imgs);
				art.setPdFisico(pf);
				art.setImmagini(img);
			}
			
			try {
				if(url!=null) {
					artdao.doSave(art);
				}
				pfDao.doSave(pf);			
			}catch (SQLException e) {
				e.printStackTrace();
			}

			
			
			
			
		}else if ( tipologia.equals("prodotto_digitale")){
			String nome = ( String) request.getParameter("nome");
			String marca = (String) request.getParameter("marca");
			String codiceSoftware= (String) request.getParameter("codiceSoftware");
			String descrizionepd= (String) request.getParameter("descrizione");
			String prezzopd= request.getParameter("prezzopf");
			String chiaviDisponibili = request.getParameter("chiaviDisponibili");
			String url = (String) request.getParameter("url");
			
			ProdottoDigitaleBean pd = new ProdottoDigitaleBean();
			ProdottoDigitaleDao pdDao = new ProdottoDigitaleDao();
			String ci="ART"+UUID.randomUUID().toString().substring(0,10);
			pd.setArticolo_codiceIdentificativo(ci);
			pd.setCategoria(tipologia);
			pd.setCodiceIdentificativo(ci);
			pd.setCodiceSoftware(codiceSoftware);
			pd.setDataUltimaPromozione(date);
			pd.setDescrizione(descrizionepd);
			pd.setDisponibilita(true);
			pd.setEnteErogatore(marca);
			pd.setNome(nome);
			pd.setNumeroChiavi(Integer.parseInt(chiaviDisponibili));
			pd.setPrezzo(Float.parseFloat(prezzopd));
			
			ArticoloCompletoBean art = new ArticoloCompletoBean();
			ArticoloCompletoDao artdao = new ArticoloCompletoDao();
			ImmagineBean imgs = new ImmagineBean();
			if(url!=null) {
				imgs.setUrl(url);
				imgs.setArticolo_codiceIdentificativo(ci);
				ArrayList<ImmagineBean> img = new ArrayList<ImmagineBean>();
				img.add(imgs);
				art.setPdDigitale(pd);
				art.setImmagini(img);
			}
			
			try {
				if(url!=null) {
					artdao.doSave(art);
				}
				pdDao.doSave(pd);			
			}catch (SQLException e) {
				e.printStackTrace();
			}

			
		}else if ( tipologia.equals("servizio")){
			String nome = (String) request.getParameter("nome");
			String codiceServizio= (String) request.getParameter("codiceServizio");
			String prezzosv= request.getParameter("prezzo");
			String descrizionesv= (String) request.getParameter("descrizione");
			String durata =  request.getParameter("durata");
			String url = (String) request.getParameter("url");
			
			 ServizioBean servizio = new ServizioBean();
			 ServizioDao servDao = new ServizioDao();
			 
			 String ci="ART"+UUID.randomUUID().toString().substring(0,10);
			 servizio.setArticolo_codiceIdentificativo(ci);
			 servizio.setCategoria(tipologia);
			 servizio.setCodiceIdentificativo(ci);
			 servizio.setCodiceServizio(codiceServizio);
			 servizio.setDataUltimaPromozione(date);
			 servizio.setDescrizione(descrizionesv);
			 servizio.setDisponibilita(true);
			 servizio.setDurata(Double.parseDouble(durata));
			 servizio.setEnteErogatore("tecnotime");
			 servizio.setNome(nome);
			 servizio.setPrezzo(Float.parseFloat(prezzosv));
			 
			 ArticoloCompletoBean art = new ArticoloCompletoBean();
				ArticoloCompletoDao artdao = new ArticoloCompletoDao();
				ImmagineBean imgs = new ImmagineBean();
				if(url!=null) {
					imgs.setUrl(url);
					imgs.setArticolo_codiceIdentificativo(ci);
					ArrayList<ImmagineBean> img = new ArrayList<ImmagineBean>();
					img.add(imgs);
					art.setServizio(servizio);
					art.setImmagini(img);
				}
				
				try {
					if(url!=null) {
						artdao.doSave(art);
					}
					servDao.doSave(servizio);			
				}catch (SQLException e) {
					e.printStackTrace();
				}
		}

		
		if(tipologia!=null) {
			request.setAttribute("success", "articolo aggiunto con successo");
		}
		RequestDispatcher disp = request.getRequestDispatcher("amministratore/aggiungiProdotto.jsp");
		disp.forward(request, response);
	
		/*	//processori
		nomecompleto, marca, socket, datarilascio, Watt
		
		//scheda madre
		nomecompleto, marca, socket, dimensione, PCI, SupportoRam, Watt
		
		//scheda video 
		nomecompleto, marca, PCI, vram, tipoRam, Watt
	
		//alimentatori 
		nomecompleto, marca, watt
		
		//case
		nomecompleto, dimensione */
	
	}
}
