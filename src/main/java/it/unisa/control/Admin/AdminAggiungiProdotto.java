package it.unisa.control.Admin;

import jakarta.servlet.ServletException;
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
import it.unisa.model.DAO.Articoli.ProdottoFisicoDao;
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
import it.unisa.model.beans.ProdottoFisicoBean;

/**
 * Servlet implementation class AdminAggiungiProdotto
 */
@WebServlet("/AdminAggiungiProdotto")
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

	
		
		String tipologia = (String) request.getAttribute("tipologia");
		Date date = Date.valueOf(LocalDate.now());
		
		if (tipologia.equals("processore")) {
			String nomecompletocpu = (String) request.getAttribute("nomecompleto");
			String marcacpu = (String) request.getAttribute("marca");
			String socketcpu = (String) request.getAttribute("datarilascio");	
			int wattcpu = (int) request.getAttribute("watt");
			String seriale  = (String) request.getAttribute("seriale");
			float prezzopd = (float) request.getAttribute("prezzo");
			String descrizionepf = (String) request.getAttribute("descrizione");
			int quantitaMagazzino= (int) request.getAttribute("quantitaMagazzino");
			
			ProdottoFisicoBean pd = new ProdottoFisicoBean();
			ProdottoFisicoDao pdDao = new ProdottoFisicoDao();
			String ci="ART"+UUID.randomUUID().toString().substring(0,20);
			pd.setArticolo_codiceIdentificativo(ci);
			pd.setCategoria(tipologia);
			pd.setCodiceIdentificativo(ci);
			pd.setDataUltimaPromozione(date);
			pd.setDescrizione(descrizionepf);
			pd.setDisponibilita(true);
			pd.setEnteErogatore(marcacpu);
			pd.setNome(nomecompletocpu);
			pd.setPreassemblato(false);
			pd.setPrezzo(prezzopd);
			pd.setSeriale(seriale);
			pd.setQuantitaMagazzino(quantitaMagazzino);

			
			Processore cpu = new Processore(nomecompletocpu, marcacpu, socketcpu, date, wattcpu);
			ProcessoreDao cpuDao = new ProcessoreDao();
			try {
				pdDao.doSave(pd);
				cpuDao.doSave(cpu);				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}else  if(tipologia.equals("scheda_madre")) {
			String nomecompletomb = (String) request.getAttribute("nomecompleto");
			String marcamb = (String) request.getAttribute("marca");
			String socketmb = (String) request.getAttribute("socket");
			String dimensionemb = (String) request.getAttribute("dimensione");
			float PCImb = (float) request.getAttribute("PCI");
			int wattmb = (int) request.getAttribute("watt");
			String supportoRam = (String) request.getAttribute("supportoRam");
			String seriale  = (String) request.getAttribute("seriale");
			float prezzopd = (float) request.getAttribute("prezzo");
			String descrizionepf = (String) request.getAttribute("descrizione");
			int quantitaMagazzino= (int) request.getAttribute("quantitaMagazzino");
			String url = (String) request.getAttribute("url");
			
			ProdottoFisicoBean pd = new ProdottoFisicoBean();
			ProdottoFisicoDao pdDao = new ProdottoFisicoDao();
			String ci="ART"+UUID.randomUUID().toString().substring(0,20);
			pd.setArticolo_codiceIdentificativo(ci);
			pd.setCategoria(tipologia);
			pd.setCodiceIdentificativo(ci);
			pd.setDataUltimaPromozione(date);
			pd.setDescrizione(descrizionepf);
			pd.setDisponibilita(true);
			pd.setEnteErogatore(marcamb);
			pd.setNome(nomecompletomb);
			pd.setPreassemblato(false);
			pd.setPrezzo(prezzopd);
			pd.setSeriale(seriale);
			pd.setQuantitaMagazzino(quantitaMagazzino);
			
			ImmagineBean imgs = new ImmagineBean();
			imgs.setUrl(url);
			imgs.setArticolo_codiceIdentificativo(ci);
			ArrayList<ImmagineBean> img = new ArrayList<ImmagineBean>();
			img.add(imgs);
			
			ArticoloCompletoBean art = new ArticoloCompletoBean();
			art.setPdFisico(pd);
			art.setImmagini(img);
			ArticoloCompletoDao 
			SchedaMadre mb = new SchedaMadre(nomecompletomb, marcamb, socketmb, dimensionemb,PCImb, supportoRam,wattmb);
			SchedaMadreDao mbDao = new SchedaMadreDao();
			
			try {
				pdDao.doSave(pd);
				mbDao.doSave(mb);				
			}catch (SQLException e) {
				e.printStackTrace();
			}

			
		}else  if(tipologia.equals("scheda_video")) {
			String nomecompletogpu = (String) request.getAttribute("nomecompleto");
			String marcagpu = (String) request.getAttribute("marca");
			float PCIgpu = (float) request.getAttribute("PCI");
			int vram = (int) request.getAttribute("vram");
			String tipoRam = (String) request.getAttribute("tipoRam");
			int wattgpu= (int) request.getAttribute("watt");
			String seriale  = (String) request.getAttribute("seriale");
			float prezzopd = (float) request.getAttribute("prezzo");
			String descrizionepf = (String) request.getAttribute("descrizione");
			int quantitaMagazzino= (int) request.getAttribute("quantitaMagazzino");
			
			ProdottoFisicoBean pd = new ProdottoFisicoBean();
			ProdottoFisicoDao pdDao = new ProdottoFisicoDao();
			String ci="ART"+UUID.randomUUID().toString().substring(0,20);
			pd.setArticolo_codiceIdentificativo(ci);
			pd.setCategoria(tipologia);
			pd.setCodiceIdentificativo(ci);
			pd.setDataUltimaPromozione(date);
			pd.setDescrizione(descrizionepf);
			pd.setDisponibilita(true);
			pd.setEnteErogatore(marcagpu);
			pd.setNome(nomecompletogpu);
			pd.setPreassemblato(false);
			pd.setPrezzo(prezzopd);
			pd.setSeriale(seriale);
			pd.setQuantitaMagazzino(quantitaMagazzino);
			
			SchedaVideo gpu = new SchedaVideo(nomecompletogpu, marcagpu, PCIgpu, vram, tipoRam, wattgpu);
			SchedaVideoDAO gpuDao = new SchedaVideoDAO(); 
			
			try {
				pdDao.doSave(pd);
				gpuDao.doSave(gpu);				
			}catch (SQLException e) {
				e.printStackTrace();
			}

		
		}else  if(tipologia.equals("alimentatori")) {
			String nomecompletopsu= (String) request.getAttribute("nomecompleto");
			String marcapsu = (String) request.getAttribute("marca");
			int wattpsu = (int) request.getAttribute("watt");
			String seriale  = (String) request.getAttribute("seriale");
			float prezzopd = (float) request.getAttribute("prezzo");
			String descrizionepf = (String) request.getAttribute("descrizione");
			int quantitaMagazzino= (int) request.getAttribute("quantitaMagazzino");
			
			
			ProdottoFisicoBean pd = new ProdottoFisicoBean();
			ProdottoFisicoDao pdDao = new ProdottoFisicoDao();
			String ci="ART"+UUID.randomUUID().toString().substring(0,20);
			pd.setArticolo_codiceIdentificativo(ci);
			pd.setCategoria(tipologia);
			pd.setCodiceIdentificativo(ci);
			pd.setDataUltimaPromozione(date);
			pd.setDescrizione(descrizionepf);
			pd.setDisponibilita(true);
			pd.setEnteErogatore(marcapsu);
			pd.setNome(nomecompletopsu);
			pd.setPreassemblato(false);
			pd.setPrezzo(prezzopd);
			pd.setSeriale(seriale);
			pd.setQuantitaMagazzino(quantitaMagazzino);
			
			Alimentatore psu = new Alimentatore(nomecompletopsu, marcapsu, wattpsu);
			AlimentatoreDao psuDao = new AlimentatoreDao();
			
			try {
				pdDao.doSave(pd);
				psuDao.doSave(psu);				
			}catch (SQLException e) {
				e.printStackTrace();
			}

		
		
		}else if (tipologia.equals("_case")) {
			String nomecompletocase= (String) request.getAttribute("nomecompleto");
			String dimensionecase = (String) request.getAttribute("dimensione");
			String marcacase =  (String) request.getAttribute("marca");
			String seriale  = (String) request.getAttribute("seriale");
			float prezzopd = (float) request.getAttribute("prezzo");
			String descrizionepf = (String) request.getAttribute("descrizione");
			int quantitaMagazzino= (int) request.getAttribute("quantitaMagazzino");
			
			
			ProdottoFisicoBean pd = new ProdottoFisicoBean();
			ProdottoFisicoDao pdDao = new ProdottoFisicoDao();
			String ci="ART"+UUID.randomUUID().toString().substring(0,20);
			pd.setArticolo_codiceIdentificativo(ci);
			pd.setCategoria(tipologia);
			pd.setCodiceIdentificativo(ci);
			pd.setDataUltimaPromozione(date);
			pd.setDescrizione(descrizionepf);
			pd.setDisponibilita(true);
			pd.setEnteErogatore(marcacase);
			pd.setNome(nomecompletocase);
			pd.setPreassemblato(false);
			pd.setPrezzo(prezzopd);
			pd.setSeriale(seriale);
			pd.setQuantitaMagazzino(quantitaMagazzino);
			
			Case contenitore = new Case(nomecompletocase, dimensionecase);
			CaseDao caseDao = new CaseDao();
		
			try {
				pdDao.doSave(pd);
				caseDao.doSave(contenitore);				
			}catch (SQLException e) {
				e.printStackTrace();
			}

			
		
		}else if (tipologia.equals("altro")) {
			String seriale  = (String) request.getAttribute("seriale");
			String prezzopd = (String) request.getAttribute("prezzo");
			
			String descrizionepf = (String) request.getAttribute("descrizione");
			String quantitaMagazzino= (String) request.getAttribute("quantitaMagazzino");
			
			ProdottoFisicoBean pd = new ProdottoFisicoBean();
			ProdottoFisicoDao pdDao = new ProdottoFisicoDao();
			String ci="ART"+UUID.randomUUID().toString().substring(0,20);
			pd.setArticolo_codiceIdentificativo(ci);
			pd.setCategoria(tipologia);
			pd.setCodiceIdentificativo(ci);
			pd.setDataUltimaPromozione(date);
			pd.setDescrizione(descrizionepf);
			pd.setDisponibilita(true);
			pd.setEnteErogatore(marcacase);
			pd.setNome(nomecompletocase);
			pd.setPreassemblato(false);
			pd.setPrezzo(prezzopd);
			pd.setSeriale(seriale);
			pd.setQuantitaMagazzino(quantitaMagazzino);
			
			
			
			
		}else if ( tipologia.equals("prodotto_digitale")){
			
			String codiceSoftware= (String) request.getAttribute("codiceSoftware");
			String descrizionepd= (String) request.getAttribute("descrizione");
			String prezzopf= (String) request.getAttribute("prezzopf");
			String chiaviDisponibili = (String) request.getAttribute("chiaviDisponibili");
			
		}else if ( tipologia.equals("servizio")){
			
			String codiceServizio= (String) request.getAttribute("codiceServizio");
			String prezzosv= (String) request.getAttribute("prezzo");
			String descrizionesv= (String) request.getAttribute("descrizione");
			String durata = (String) request.getAttribute("durata");
		}
		
		
		
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
