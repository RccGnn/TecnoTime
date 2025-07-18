package it.unisa.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import it.unisa.model.DAO.Account.AccountDao;
import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.DAO.Articoli.ArticoloDao;
import it.unisa.model.DAO.Articoli.ProdottoDigitaleDao;
import it.unisa.model.DAO.Articoli.ProdottoFisicoDao;
import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.DAO.ComponentiFisici.CaseDao;
import it.unisa.model.DAO.ComponentiFisici.ProcessoreDao;
import it.unisa.model.DAO.ComponentiFisici.RamDao;
import it.unisa.model.DAO.ComponentiFisici.SchedaMadreDao;
import it.unisa.model.DAO.ComponentiFisici.SchedaVideoDAO;
import it.unisa.model.DAO.Order.ElementoOrdineDao;
import it.unisa.model.DAO.Order.OrdineCompletoDao;
import it.unisa.model.DAO.Order.OrdineDao;
import it.unisa.model.Filters.BuildChecker;
import it.unisa.model.Filters.Case;
import it.unisa.model.Filters.Processore;
import it.unisa.model.Filters.Ram;
import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.Filters.SchedaVideo;
import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.ArticoloBean;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.CarrelloBean;
import it.unisa.model.beans.CarrelloRiempitoBean;
import it.unisa.model.beans.ElementoOrdineBean;
import it.unisa.model.beans.OrdineBean;
import it.unisa.model.beans.OrdineCompletoBean;
import it.unisa.model.beans.ProdottoDigitaleBean;
import it.unisa.model.beans.ProdottoFisicoBean;
import it.unisa.model.beans.Ruoli;
import it.unisa.model.beans.ServizioBean;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
		 // Intel Core i9-13900K
        ProdottoFisicoBean i9_13900K = new ProdottoFisicoBean();
        i9_13900K.setCodiceIdentificativo("CG23");
        i9_13900K.setArticolo_codiceIdentificativo("CG23");
        i9_13900K.setSeriale("20240101BK0037");
        i9_13900K.setCategoria("Processori");
           i9_13900K.setDataUltimaPromozione(new Date(System.currentTimeMillis()));    
        i9_13900K.setEnteErogatore("Intel"); // marca
        i9_13900K.setNome("Intel Core i9-13900K");
        i9_13900K.setPreassemblato(false); // as per instruction
        i9_13900K.setDisponibilita(true); // as per instruction
        i9_13900K.setQuantitaMagazzino(50); // as per instruction
        i9_13900K.setDescrizione("High-performance desktop processor."); // Placeholder description
        i9_13900K.setPrezzo(549.99f); // Real price (approx. current market price)
        //System.out.println(i9_13900K);

        // Intel Core i9-12900E
        ProdottoFisicoBean i9_12900E = new ProdottoFisicoBean();
        i9_12900E.setCodiceIdentificativo("LO12");
        i9_12900E.setArticolo_codiceIdentificativo("LO12");
        i9_12900E.setSeriale("20220904AA0001");
        i9_12900E.setCategoria("Processori");
           i9_12900E.setDataUltimaPromozione(new Date(System.currentTimeMillis()));    
        i9_12900E.setEnteErogatore("Intel");
        i9_12900E.setNome("Intel Core i9-12900E");
        i9_12900E.setPreassemblato(false);
        i9_12900E.setDisponibilita(true);
        i9_12900E.setQuantitaMagazzino(50);
        i9_12900E.setDescrizione("Embedded desktop processor.");
        i9_12900E.setPrezzo(480.00f); // Estimated price
        //System.out.println(i9_12900E);

        // Intel Core i9-12900
        ProdottoFisicoBean i9_12900 = new ProdottoFisicoBean();
        i9_12900.setCodiceIdentificativo("XCG0");
        i9_12900.setArticolo_codiceIdentificativo("XCG0");
        i9_12900.setSeriale("20220104AB0002");
        i9_12900.setCategoria("Processori");
           i9_12900.setDataUltimaPromozione(new Date(System.currentTimeMillis())) ;
        i9_12900.setEnteErogatore("Intel");
        i9_12900.setNome("Intel Core i9-12900");
        i9_12900.setPreassemblato(false);
        i9_12900.setDisponibilita(true);
        i9_12900.setQuantitaMagazzino(50);
        i9_12900.setDescrizione("High-performance desktop processor.");
        i9_12900.setPrezzo(399.00f); // Real price (approx. current market price)
        //System.out.println(i9_12900);

        // Intel Core i9-12900K
        ProdottoFisicoBean i9_12900K = new ProdottoFisicoBean();
        i9_12900K.setCodiceIdentificativo("XO24");
        i9_12900K.setArticolo_codiceIdentificativo("XO24"); 
        i9_12900K.setSeriale("20211104AC0003");
        i9_12900K.setCategoria("Processori");
           i9_12900K.setDataUltimaPromozione(new Date(System.currentTimeMillis())); 
        i9_12900K.setEnteErogatore("Intel");
        i9_12900K.setNome("Intel Core i9-12900K");
        i9_12900K.setPreassemblato(false);
        i9_12900K.setDisponibilita(true);
        i9_12900K.setQuantitaMagazzino(50);
        i9_12900K.setDescrizione("High-performance unlocked desktop processor.");
        i9_12900K.setPrezzo(379.00f); // Real price (approx. current market price)
        //System.out.println(i9_12900K);

        // Intel Core i9-12900KF
        ProdottoFisicoBean i9_12900KF = new ProdottoFisicoBean();
        i9_12900KF.setCodiceIdentificativo("XVQ3");
        i9_12900KF.setArticolo_codiceIdentificativo("XVQ3"); 
        i9_12900KF.setSeriale("20211104AD0004");
        i9_12900KF.setCategoria("Processori");
           i9_12900KF.setDataUltimaPromozione(new Date(System.currentTimeMillis())) ;   
        i9_12900KF.setEnteErogatore("Intel");
        i9_12900KF.setNome("Intel Core i9-12900KF");
        i9_12900KF.setPreassemblato(false);
        i9_12900KF.setDisponibilita(true);
        i9_12900KF.setQuantitaMagazzino(50);
        i9_12900KF.setDescrizione("High-performance unlocked desktop processor without integrated graphics.");
        i9_12900KF.setPrezzo(360.00f); // Real price (approx. current market price)
       // System.out.println(i9_12900KF);

        // Intel Core i9-12900KS
        ProdottoFisicoBean i9_12900KS = new ProdottoFisicoBean();
        i9_12900KS.setCodiceIdentificativo("CG245");
        i9_12900KS.setArticolo_codiceIdentificativo("CG245"); 
        i9_12900KS.setSeriale("20211104AE0005");
        i9_12900KS.setCategoria("Processori");
           i9_12900KS.setDataUltimaPromozione(new Date(System.currentTimeMillis())); 
        i9_12900KS.setEnteErogatore("Intel");
        i9_12900KS.setNome("Intel Core i9-12900KS");
        i9_12900KS.setPreassemblato(false);
        i9_12900KS.setDisponibilita(true);
        i9_12900KS.setQuantitaMagazzino(50);
        i9_12900KS.setDescrizione("Special edition high-performance unlocked desktop processor.");
        i9_12900KS.setPrezzo(699.00f); // Estimated price (high-end special edition)
       // System.out.println(i9_12900KS);

        // Intel Core i9-12900T
        ProdottoFisicoBean i9_12900T = new ProdottoFisicoBean();
        i9_12900T.setCodiceIdentificativo("SFGB13");
        i9_12900T.setArticolo_codiceIdentificativo("SFGB13"); 
        i9_12900T.setSeriale("20220405AF0006");
        i9_12900T.setCategoria("Processori");
           i9_12900T.setDataUltimaPromozione(new Date(System.currentTimeMillis()));    
        i9_12900T.setEnteErogatore("Intel");
        i9_12900T.setNome("Intel Core i9-12900T");
        i9_12900T.setPreassemblato(false);
        i9_12900T.setDisponibilita(true);
        i9_12900T.setQuantitaMagazzino(50);
        i9_12900T.setDescrizione("Power-efficient desktop processor.");
        i9_12900T.setPrezzo(450.00f); // Estimated price
       // System.out.println(i9_12900T);

        // Intel Core i9-12900TE
        ProdottoFisicoBean i9_12900TE = new ProdottoFisicoBean();
        i9_12900TE.setCodiceIdentificativo("GTFS34");
        i9_12900TE.setArticolo_codiceIdentificativo("GTFS34");
        i9_12900TE.setSeriale("20220104AG0007");
        i9_12900TE.setCategoria("Processori");
           i9_12900TE.setDataUltimaPromozione(new Date(System.currentTimeMillis()));    
        i9_12900TE.setEnteErogatore("Intel");
        i9_12900TE.setNome("Intel Core i9-12900TE");
        i9_12900TE.setPreassemblato(false);
        i9_12900TE.setDisponibilita(true);
        i9_12900TE.setQuantitaMagazzino(50);
        i9_12900TE.setDescrizione("Embedded power-efficient desktop processor.");
        i9_12900TE.setPrezzo(460.00f); // Estimated price
       // System.out.println(i9_12900TE);

        // Intel Core i7-12700
        ProdottoFisicoBean i7_12700 = new ProdottoFisicoBean();
        i7_12700.setCodiceIdentificativo("SLF134");
        i7_12700.setArticolo_codiceIdentificativo("SLF134");
        i7_12700.setSeriale("20220104AH0008");
        i7_12700.setCategoria("Processori");
           i7_12700.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        i7_12700.setEnteErogatore("Intel");
        i7_12700.setNome("Intel Core i7-12700");
        i7_12700.setPreassemblato(false);
        i7_12700.setDisponibilita(true);
        i7_12700.setQuantitaMagazzino(50);
        i7_12700.setDescrizione("Mainstream desktop processor.");
        i7_12700.setPrezzo(279.00f); // Real price (approx. current market price)
      //  System.out.println(i7_12700);

        // Intel Core i7-12700E
        ProdottoFisicoBean i7_12700E = new ProdottoFisicoBean();
        i7_12700E.setCodiceIdentificativo("SSF345");
        i7_12700E.setArticolo_codiceIdentificativo("SSF345");
        i7_12700E.setSeriale("20220104AI0009");
        i7_12700E.setCategoria("Processori");
           i7_12700E.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i7_12700E.setEnteErogatore("Intel");
        i7_12700E.setNome("Intel Core i7-12700E");
        i7_12700E.setPreassemblato(false);
        i7_12700E.setDisponibilita(true);
        i7_12700E.setQuantitaMagazzino(50);
        i7_12700E.setDescrizione("Embedded mainstream desktop processor.");
        i7_12700E.setPrezzo(285.00f); // Estimated price
     //   System.out.println(i7_12700E);

        // Intel Core i7-12700F
        ProdottoFisicoBean i7_12700F = new ProdottoFisicoBean();
        i7_12700F.setCodiceIdentificativo("LP456");
        i7_12700F.setArticolo_codiceIdentificativo("LP456"); 
        i7_12700F.setSeriale("20220104AJ0010");
        i7_12700F.setCategoria("Processori");
           i7_12700F.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i7_12700F.setEnteErogatore("Intel");
        i7_12700F.setNome("Intel Core i7-12700F");
        i7_12700F.setPreassemblato(false);
        i7_12700F.setDisponibilita(true);
        i7_12700F.setQuantitaMagazzino(50);
        i7_12700F.setDescrizione("Mainstream desktop processor without integrated graphics.");
        i7_12700F.setPrezzo(265.00f); // Real price (approx. current market price)
    //    System.out.println(i7_12700F);

        // Intel Core i7-12700K
        ProdottoFisicoBean i7_12700K = new ProdottoFisicoBean();
        i7_12700K.setCodiceIdentificativo("ZX134");
        i7_12700K.setArticolo_codiceIdentificativo("ZX134");
        i7_12700K.setSeriale("20220104AK0011");
        i7_12700K.setCategoria("Processori");
           i7_12700K.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i7_12700K.setEnteErogatore("Intel");
        i7_12700K.setNome("Intel Core i7-12700K");
        i7_12700K.setPreassemblato(false);
        i7_12700K.setDisponibilita(true);
        i7_12700K.setQuantitaMagazzino(50);
        i7_12700K.setDescrizione("High-performance unlocked desktop processor.");
        i7_12700K.setPrezzo(290.00f); // Real price (approx. current market price)
     //   System.out.println(i7_12700K);

        // Intel Core i7-12700KF
        ProdottoFisicoBean i7_12700KF = new ProdottoFisicoBean();
        i7_12700KF.setCodiceIdentificativo("SFG45");
        i7_12700KF.setArticolo_codiceIdentificativo("SFG45"); 
        i7_12700KF.setSeriale("20211104AL0012");
        i7_12700KF.setCategoria("Processori");
           i7_12700KF.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i7_12700KF.setEnteErogatore("Intel");
        i7_12700KF.setNome("Intel Core i7-12700KF");
        i7_12700KF.setPreassemblato(false);
        i7_12700KF.setDisponibilita(true);
        i7_12700KF.setQuantitaMagazzino(50);
        i7_12700KF.setDescrizione("High-performance unlocked desktop processor without integrated graphics.");
        i7_12700KF.setPrezzo(280.00f); // Real price (approx. current market price)
   //     System.out.println(i7_12700KF);

        // Intel Core i7-12700T
        ProdottoFisicoBean i7_12700T = new ProdottoFisicoBean();
        i7_12700T.setCodiceIdentificativo("ZCGG1");
        i7_12700T.setArticolo_codiceIdentificativo("ZCGG1"); 
        i7_12700T.setSeriale("20211104AM0013");
        i7_12700T.setCategoria("Processori");
           i7_12700T.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i7_12700T.setEnteErogatore("Intel");
        i7_12700T.setNome("Intel Core i7-12700T");
        i7_12700T.setPreassemblato(false);
        i7_12700T.setDisponibilita(true);
        i7_12700T.setQuantitaMagazzino(50);
        i7_12700T.setDescrizione("Power-efficient desktop processor.");
        i7_12700T.setPrezzo(250.00f); // Estimated price
     //   System.out.println(i7_12700T);

        // Intel Core i7-12700TE
        ProdottoFisicoBean i7_12700TE = new ProdottoFisicoBean();
        i7_12700TE.setCodiceIdentificativo("ZFASX12");
        i7_12700TE.setArticolo_codiceIdentificativo("ZFASX12");
        i7_12700TE.setSeriale("20220104AN0014");
        i7_12700TE.setCategoria("Processori");
           i7_12700TE.setDataUltimaPromozione(new Date(System.currentTimeMillis()));    
        i7_12700TE.setEnteErogatore("Intel");
        i7_12700TE.setNome("Intel Core i7-12700TE");
        i7_12700TE.setPreassemblato(false);
        i7_12700TE.setDisponibilita(true);
        i7_12700TE.setQuantitaMagazzino(50);
        i7_12700TE.setDescrizione("Embedded power-efficient desktop processor.");
        i7_12700TE.setPrezzo(260.00f); // Estimated price
     //   System.out.println(i7_12700TE);

        // Intel Core i5-13600K
        ProdottoFisicoBean i5_13600K = new ProdottoFisicoBean();
        i5_13600K.setCodiceIdentificativo("SX98Z");
        i5_13600K.setArticolo_codiceIdentificativo("SX98Z");
        i5_13600K.setSeriale("20220104AO0015");
        i5_13600K.setCategoria("Processori");
           i5_13600K.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        i5_13600K.setEnteErogatore("Intel");
        i5_13600K.setNome("Intel Core i5-13600K");
        i5_13600K.setPreassemblato(false);
        i5_13600K.setDisponibilita(true);
        i5_13600K.setQuantitaMagazzino(50);
        i5_13600K.setDescrizione("High-performance unlocked desktop processor.");
        i5_13600K.setPrezzo(289.00f); // Real price (approx. current market price)
   //     System.out.println(i5_13600K);

        // Intel Core i5-13600KF
        ProdottoFisicoBean i5_13600KF = new ProdottoFisicoBean();
        i5_13600KF.setCodiceIdentificativo("KHVV32");
        i5_13600KF.setArticolo_codiceIdentificativo("KHVV32");
        i5_13600KF.setSeriale("20221020AP0016");
        i5_13600KF.setCategoria("Processori");
           i5_13600KF.setDataUltimaPromozione(new Date(System.currentTimeMillis()));
        i5_13600KF.setEnteErogatore("Intel");
        i5_13600KF.setNome("Intel Core i5-13600KF");
        i5_13600KF.setPreassemblato(false);
        i5_13600KF.setDisponibilita(true);
        i5_13600KF.setQuantitaMagazzino(50);
        i5_13600KF.setDescrizione("High-performance unlocked desktop processor without integrated graphics.");
        i5_13600KF.setPrezzo(270.00f); // Real price (approx. current market price)
 //       System.out.println(i5_13600KF);

        // Intel Processor 300
        ProdottoFisicoBean processor300 = new ProdottoFisicoBean();
        processor300.setCodiceIdentificativo("KPO24");
        processor300.setArticolo_codiceIdentificativo("KPO24");
        processor300.setSeriale("20221020AQ0017");
        processor300.setCategoria("Processori");
           processor300.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        processor300.setEnteErogatore("Intel");
        processor300.setNome("Intel Processor 300");
        processor300.setPreassemblato(false);
        processor300.setDisponibilita(true);
        processor300.setQuantitaMagazzino(50);
        processor300.setDescrizione("Entry-level desktop processor.");
        processor300.setPrezzo(85.00f); // Real price (approx. current market price)
 //       System.out.println(processor300);

        // Intel Core i9-14900
        ProdottoFisicoBean i9_14900 = new ProdottoFisicoBean();
        i9_14900.setCodiceIdentificativo("2D134");
        i9_14900.setArticolo_codiceIdentificativo("2D134");
        i9_14900.setSeriale("20240108AR0018");
        i9_14900.setCategoria("Processori");
           i9_14900.setDataUltimaPromozione(new Date(System.currentTimeMillis()));    
        i9_14900.setEnteErogatore("Intel");
        i9_14900.setNome("Intel Core i9-14900");
        i9_14900.setPreassemblato(false);
        i9_14900.setDisponibilita(true);
        i9_14900.setQuantitaMagazzino(50);
        i9_14900.setDescrizione("High-performance desktop processor.");
        i9_14900.setPrezzo(589.00f); // Estimated price
  //      System.out.println(i9_14900);

        // Intel Core i9-14900F
        ProdottoFisicoBean i9_14900F = new ProdottoFisicoBean();
        i9_14900F.setCodiceIdentificativo("2FV14");
        i9_14900F.setArticolo_codiceIdentificativo("2FV14");
        i9_14900F.setSeriale("20240109AS0019");
        i9_14900F.setCategoria("Processori");
           i9_14900F.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i9_14900F.setEnteErogatore("Intel");
        i9_14900F.setNome("Intel Core i9-14900F");
        i9_14900F.setPreassemblato(false);
        i9_14900F.setDisponibilita(true);
        i9_14900F.setQuantitaMagazzino(50);
        i9_14900F.setDescrizione("High-performance desktop processor without integrated graphics.");
        i9_14900F.setPrezzo(569.00f); // Estimated price
   //     System.out.println(i9_14900F);

        // Intel Core i9-14900K
        ProdottoFisicoBean i9_14900K = new ProdottoFisicoBean();
        i9_14900K.setCodiceIdentificativo("145VVCA");
        i9_14900K.setArticolo_codiceIdentificativo("145VVCA");
        i9_14900K.setSeriale("20240109AT0020");
        i9_14900K.setCategoria("Processori");
           i9_14900K.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i9_14900K.setEnteErogatore("Intel");
        i9_14900K.setNome("Intel Core i9-14900K");
        i9_14900K.setPreassemblato(false);
        i9_14900K.setDisponibilita(true);
        i9_14900K.setQuantitaMagazzino(50);
        i9_14900K.setDescrizione("High-performance unlocked desktop processor.");
        i9_14900K.setPrezzo(579.00f); // Real price (approx. current market price)
 //       System.out.println(i9_14900K);

        // Intel Core i9-14900KF
        ProdottoFisicoBean i9_14900KF = new ProdottoFisicoBean();
        i9_14900KF.setCodiceIdentificativo("GART24");
        i9_14900KF.setArticolo_codiceIdentificativo("GART24");
        i9_14900KF.setSeriale("20231017AU0021");
        i9_14900KF.setCategoria("Processori");
           i9_14900KF.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        i9_14900KF.setEnteErogatore("Intel");
        i9_14900KF.setNome("Intel Core i9-14900KF");
        i9_14900KF.setPreassemblato(false);
        i9_14900KF.setDisponibilita(true);
        i9_14900KF.setQuantitaMagazzino(50);
        i9_14900KF.setDescrizione("High-performance unlocked desktop processor without integrated graphics.");
        i9_14900KF.setPrezzo(559.00f); // Real price (approx. current market price)
 //       System.out.println(i9_14900KF);

        // Intel Core i9-14900KS
        ProdottoFisicoBean i9_14900KS = new ProdottoFisicoBean();
        i9_14900KS.setCodiceIdentificativo("FAL134");
        i9_14900KS.setArticolo_codiceIdentificativo("FAL134");
        i9_14900KS.setSeriale("20231017AV0022");
        i9_14900KS.setCategoria("Processori");
           i9_14900KS.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i9_14900KS.setEnteErogatore("Intel");
        i9_14900KS.setNome("Intel Core i9-14900KS");
        i9_14900KS.setPreassemblato(false);
        i9_14900KS.setDisponibilita(true);
        i9_14900KS.setQuantitaMagazzino(50);
        i9_14900KS.setDescrizione("Special edition high-performance unlocked desktop processor.");
        i9_14900KS.setPrezzo(729.00f); // Estimated price (very high-end special edition)
 //       System.out.println(i9_14900KS);

        // Intel Core i9-14900T
        ProdottoFisicoBean i9_14900T = new ProdottoFisicoBean();
        i9_14900T.setCodiceIdentificativo("WGD134");
        i9_14900T.setArticolo_codiceIdentificativo("WGD134"); 
        i9_14900T.setSeriale("20240109AW0023");
        i9_14900T.setCategoria("Processori");
           i9_14900T.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        i9_14900T.setEnteErogatore("Intel");
        i9_14900T.setNome("Intel Core i9-14900T");
        i9_14900T.setPreassemblato(false);
        i9_14900T.setDisponibilita(true);
        i9_14900T.setQuantitaMagazzino(50);
        i9_14900T.setDescrizione("Power-efficient desktop processor.");
        i9_14900T.setPrezzo(530.00f); // Estimated price
  //      System.out.println(i9_14900T);

        // Intel Core i9-14901E
        ProdottoFisicoBean i9_14901E = new ProdottoFisicoBean();
        i9_14901E.setCodiceIdentificativo("XVBMY13");
        i9_14901E.setArticolo_codiceIdentificativo("XVBMY13");
        i9_14901E.setSeriale("20240109AX0024");
        i9_14901E.setCategoria("Processori");
           i9_14901E.setDataUltimaPromozione(new Date(System.currentTimeMillis())); 
        i9_14901E.setEnteErogatore("Intel");
        i9_14901E.setNome("Intel Core i9-14901E");
        i9_14901E.setPreassemblato(false);
        i9_14901E.setDisponibilita(true);
        i9_14901E.setQuantitaMagazzino(50);
        i9_14901E.setDescrizione("Embedded high-performance desktop processor.");
        i9_14901E.setPrezzo(599.00f); // Estimated price
 //       System.out.println(i9_14901E);

        // Intel Core i9-14901KE
        ProdottoFisicoBean i9_14901KE = new ProdottoFisicoBean();
        i9_14901KE.setCodiceIdentificativo("GHJN13456");
        i9_14901KE.setArticolo_codiceIdentificativo("GHJN13456"); 
        i9_14901KE.setSeriale("20240109AY0025");
        i9_14901KE.setCategoria("Processori");
           i9_14901KE.setDataUltimaPromozione(new Date(System.currentTimeMillis()));
        i9_14901KE.setEnteErogatore("Intel");
        i9_14901KE.setNome("Intel Core i9-14901KE");
        i9_14901KE.setPreassemblato(false);
        i9_14901KE.setDisponibilita(true);
        i9_14901KE.setQuantitaMagazzino(50);
        i9_14901KE.setDescrizione("Embedded high-performance unlocked desktop processor.");
        i9_14901KE.setPrezzo(620.00f); // Estimated price
  //      System.out.println(i9_14901KE);

        // Intel Core i9-14901TE
        ProdottoFisicoBean i9_14901TE = new ProdottoFisicoBean();
        i9_14901TE.setCodiceIdentificativo("PVGHM467");
        i9_14901TE.setArticolo_codiceIdentificativo("PVGHM467");
        i9_14901TE.setSeriale("20231017AZ0026");
        i9_14901TE.setCategoria("Processori");
           i9_14901TE.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        i9_14901TE.setEnteErogatore("Intel");
        i9_14901TE.setNome("Intel Core i9-14901TE");
        i9_14901TE.setPreassemblato(false);
        i9_14901TE.setDisponibilita(true);
        i9_14901TE.setQuantitaMagazzino(50);
        i9_14901TE.setDescrizione("Embedded power-efficient desktop processor.");
        i9_14901TE.setPrezzo(540.00f); // Estimated price
  //      System.out.println(i9_14901TE);

        // Intel Core i7-14700
        ProdottoFisicoBean i7_14700 = new ProdottoFisicoBean();
        i7_14700.setCodiceIdentificativo("DGO246");
        i7_14700.setArticolo_codiceIdentificativo("DGO246"); 
        i7_14700.setSeriale("20240109BA0027");
        i7_14700.setCategoria("Processori");
           i7_14700.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i7_14700.setEnteErogatore("Intel");
        i7_14700.setNome("Intel Core i7-14700");
        i7_14700.setPreassemblato(false);
        i7_14700.setDisponibilita(true);
        i7_14700.setQuantitaMagazzino(50);
        i7_14700.setDescrizione("Mainstream desktop processor.");
        i7_14700.setPrezzo(399.00f); // Real price (approx. current market price)
//        System.out.println(i7_14700);

        // Intel Core i7-14700F
        ProdottoFisicoBean i7_14700F = new ProdottoFisicoBean();
        i7_14700F.setCodiceIdentificativo("ASXCVV13445");
        i7_14700F.setArticolo_codiceIdentificativo("ASXCVV13445");
        i7_14700F.setSeriale("20240109BB0028");
        i7_14700F.setCategoria("Processori");
           i7_14700F.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        i7_14700F.setEnteErogatore("Intel");
        i7_14700F.setNome("Intel Core i7-14700F");
        i7_14700F.setPreassemblato(false);
        i7_14700F.setDisponibilita(true);
        i7_14700F.setQuantitaMagazzino(50);
        i7_14700F.setDescrizione("Mainstream desktop processor without integrated graphics.");
        i7_14700F.setPrezzo(379.00f); // Real price (approx. current market price)
//        System.out.println(i7_14700F);

        // Intel Core i7-14700K
        ProdottoFisicoBean i7_14700K = new ProdottoFisicoBean();
        i7_14700K.setCodiceIdentificativo("FNGNFR98");
        i7_14700K.setArticolo_codiceIdentificativo("FNGNFR98"); 
        i7_14700K.setSeriale("20240109BC0029");
        i7_14700K.setCategoria("Processori");
           i7_14700K.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        i7_14700K.setEnteErogatore("Intel");
        i7_14700K.setNome("Intel Core i7-14700K");
        i7_14700K.setPreassemblato(false);
        i7_14700K.setDisponibilita(true);
        i7_14700K.setQuantitaMagazzino(50);
        i7_14700K.setDescrizione("High-performance unlocked desktop processor.");
        i7_14700K.setPrezzo(399.00f); // Real price (approx. current market price)
  //      System.out.println(i7_14700K);

        // Intel Core i7-14700KF
        ProdottoFisicoBean i7_14700KF = new ProdottoFisicoBean();
        i7_14700KF.setCodiceIdentificativo("24RFSCD");
        i7_14700KF.setArticolo_codiceIdentificativo("24RFSCD");
        i7_14700KF.setSeriale("20231017BD0030");
        i7_14700KF.setCategoria("Processori");
           i7_14700KF.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i7_14700KF.setEnteErogatore("Intel");
        i7_14700KF.setNome("Intel Core i7-14700KF");
        i7_14700KF.setPreassemblato(false);
        i7_14700KF.setDisponibilita(true);
        i7_14700KF.setQuantitaMagazzino(50);
        i7_14700KF.setDescrizione("High-performance unlocked desktop processor without integrated graphics.");
        i7_14700KF.setPrezzo(379.00f); // Real price (approx. current market price)
    //    System.out.println(i7_14700KF);

        // Intel Core i7-14700T
        ProdottoFisicoBean i7_14700T = new ProdottoFisicoBean();
        i7_14700T.setCodiceIdentificativo("SAC1355");
        i7_14700T.setArticolo_codiceIdentificativo("SAC1355");
        i7_14700T.setSeriale("20231017BE0031");
        i7_14700T.setCategoria("Processori");
           i7_14700T.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        i7_14700T.setEnteErogatore("Intel");
        i7_14700T.setNome("Intel Core i7-14700T");
        i7_14700T.setPreassemblato(false);
        i7_14700T.setDisponibilita(true);
        i7_14700T.setQuantitaMagazzino(50);
        i7_14700T.setDescrizione("Power-efficient desktop processor.");
        i7_14700T.setPrezzo(350.00f); // Estimated price
  //      System.out.println(i7_14700T);

        // Intel Core i7-14701E
        ProdottoFisicoBean i7_14701E = new ProdottoFisicoBean();
        i7_14701E.setCodiceIdentificativo("SAXVBFS345");
        i7_14701E.setArticolo_codiceIdentificativo("SAXVBFS345");
        i7_14701E.setSeriale("20240109BF0032");
        i7_14701E.setCategoria("Processori");
           i7_14701E.setDataUltimaPromozione(new Date(System.currentTimeMillis())); 
        i7_14701E.setEnteErogatore("Intel");
        i7_14701E.setNome("Intel Core i7-14701E");
        i7_14701E.setPreassemblato(false);
        i7_14701E.setDisponibilita(true);
        i7_14701E.setQuantitaMagazzino(50);
        i7_14701E.setDescrizione("Embedded mainstream desktop processor.");
        i7_14701E.setPrezzo(410.00f); // Estimated price
 //       System.out.println(i7_14701E);

        // Intel Core i7-14701TE
        ProdottoFisicoBean i7_14701TE = new ProdottoFisicoBean();
        i7_14701TE.setCodiceIdentificativo("SZXLVK1355");
        i7_14701TE.setArticolo_codiceIdentificativo("SZXLVK1355");
        i7_14701TE.setSeriale("20240109BG0033");
        i7_14701TE.setCategoria("Processori");
           i7_14701TE.setDataUltimaPromozione(new Date(System.currentTimeMillis()));    
        i7_14701TE.setEnteErogatore("Intel");
        i7_14701TE.setNome("Intel Core i7-14701TE");
        i7_14701TE.setPreassemblato(false);
        i7_14701TE.setDisponibilita(true);
        i7_14701TE.setQuantitaMagazzino(50);
        i7_14701TE.setDescrizione("Embedded power-efficient desktop processor.");
        i7_14701TE.setPrezzo(360.00f); // Estimated price
  //      System.out.println(i7_14701TE);

        // Intel Core i5-14500
        ProdottoFisicoBean i5_14500 = new ProdottoFisicoBean();
        i5_14500.setCodiceIdentificativo("SVCMFSDFA234");
        i5_14500.setArticolo_codiceIdentificativo("SVCMFSDFA234");
        i5_14500.setSeriale("20240109BH0034");
        i5_14500.setCategoria("Processori");
           i5_14500.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        i5_14500.setEnteErogatore("Intel");
        i5_14500.setNome("Intel Core i5-14500");
        i5_14500.setPreassemblato(false);
        i5_14500.setDisponibilita(true);
        i5_14500.setQuantitaMagazzino(50);
        i5_14500.setDescrizione("Mid-range desktop processor.");
        i5_14500.setPrezzo(239.00f); // Real price (approx. current market price)
  //      System.out.println(i5_14500);

        // Intel Core i5-14500T
        ProdottoFisicoBean i5_14500T = new ProdottoFisicoBean();
        i5_14500T.setCodiceIdentificativo("SSAFG5436");
        i5_14500T.setArticolo_codiceIdentificativo("SSAFG5436");
        i5_14500T.setSeriale("20240101BI0035");
        i5_14500T.setCategoria("Processori");
           i5_14500T.setDataUltimaPromozione(new Date(System.currentTimeMillis()));    
        i5_14500T.setEnteErogatore("Intel");
        i5_14500T.setNome("Intel Core i5-14500T");
        i5_14500T.setPreassemblato(false);
        i5_14500T.setDisponibilita(true);
        i5_14500T.setQuantitaMagazzino(50);
        i5_14500T.setDescrizione("Power-efficient mid-range desktop processor.");
        i5_14500T.setPrezzo(220.00f); // Estimated price
 //       System.out.println(i5_14500T);

        // Intel Core i5-14600
        ProdottoFisicoBean i5_14600 = new ProdottoFisicoBean();
        i5_14600.setCodiceIdentificativo("PIJASND2346");
        i5_14600.setArticolo_codiceIdentificativo("PIJASND2346");
        i5_14600.setSeriale("20240101BJ0036");
        i5_14600.setCategoria("Processori");
           i5_14600.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i5_14600.setEnteErogatore("Intel");
        i5_14600.setNome("Intel Core i5-14600");
        i5_14600.setPreassemblato(false);
        i5_14600.setDisponibilita(true);
        i5_14600.setQuantitaMagazzino(50);
        i5_14600.setDescrizione("High-end mid-range desktop processor.");
        i5_14600.setPrezzo(269.00f); // Estimated price
 //       System.out.println(i5_14600);

        // Intel Core i5-14600K
        ProdottoFisicoBean i5_14600K = new ProdottoFisicoBean();
        i5_14600K.setCodiceIdentificativo("PCVSD2345");
        i5_14600K.setArticolo_codiceIdentificativo("PCVSD2345");
        i5_14600K.setSeriale("20240101BL0038");
        i5_14600K.setCategoria("Processori");
           i5_14600K.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i5_14600K.setEnteErogatore("Intel");
        i5_14600K.setNome("Intel Core i5-14600K");
        i5_14600K.setPreassemblato(false);
        i5_14600K.setDisponibilita(true);
        i5_14600K.setQuantitaMagazzino(50);
        i5_14600K.setDescrizione("High-end unlocked mid-range desktop processor.");
        i5_14600K.setPrezzo(319.00f); // Real price (approx. current market price)
//        System.out.println(i5_14600K);

        // Intel Core i5-14600KF
        ProdottoFisicoBean i5_14600KF = new ProdottoFisicoBean();
        i5_14600KF.setCodiceIdentificativo("ADVV64134");
        i5_14600KF.setArticolo_codiceIdentificativo("ADVV64134"); 
        i5_14600KF.setSeriale("20240101BM0039");
        i5_14600KF.setCategoria("Processori");
           i5_14600KF.setDataUltimaPromozione(new Date(System.currentTimeMillis()));
        i5_14600KF.setEnteErogatore("Intel");
        i5_14600KF.setNome("Intel Core i5-14600KF");
        i5_14600KF.setPreassemblato(false);
        i5_14600KF.setDisponibilita(true);
        i5_14600KF.setQuantitaMagazzino(50);
        i5_14600KF.setDescrizione("High-end unlocked mid-range desktop processor without integrated graphics.");
        i5_14600KF.setPrezzo(300.00f); // Real price (approx. current market price)
    //    System.out.println(i5_14600KF);

        // Intel Core i5-14600T
        ProdottoFisicoBean i5_14600T = new ProdottoFisicoBean();
        i5_14600T.setCodiceIdentificativo("CPGLKK2456");
        i5_14600T.setArticolo_codiceIdentificativo("CPGLKK2456"); 
        i5_14600T.setSeriale("20240101BN0040");
        i5_14600T.setCategoria("Processori");
           i5_14600T.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i5_14600T.setEnteErogatore("Intel");
        i5_14600T.setNome("Intel Core i5-14600T");
        i5_14600T.setPreassemblato(false);
        i5_14600T.setDisponibilita(true);
        i5_14600T.setQuantitaMagazzino(50);
        i5_14600T.setDescrizione("Power-efficient high-end mid-range desktop processor.");
        i5_14600T.setPrezzo(250.00f); // Estimated price
  //      System.out.println(i5_14600T);

        // Intel Core i5-14400
        ProdottoFisicoBean i5_14400 = new ProdottoFisicoBean();
        i5_14400.setCodiceIdentificativo("CAXVSB2345");
        i5_14400.setArticolo_codiceIdentificativo("CAXVSB2345"); 
        i5_14400.setSeriale("20240101BO0041");
        i5_14400.setCategoria("Processori");
           i5_14400.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        i5_14400.setEnteErogatore("Intel");
        i5_14400.setNome("Intel Core i5-14400");
        i5_14400.setPreassemblato(false);
        i5_14400.setDisponibilita(true);
        i5_14400.setQuantitaMagazzino(50);
        i5_14400.setDescrizione("Mainstream mid-range desktop processor.");
        i5_14400.setPrezzo(209.00f); // Real price (approx. current market price)
  //      System.out.println(i5_14400);

        // Intel Core i5-14400F
        ProdottoFisicoBean i5_14400F = new ProdottoFisicoBean();
        i5_14400F.setCodiceIdentificativo("ADGVBS256");
        i5_14400F.setArticolo_codiceIdentificativo("ADGVBS256"); 
        i5_14400F.setSeriale("20220104BP0042");
        i5_14400F.setCategoria("Processori");
           i5_14400F.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i5_14400F.setEnteErogatore("Intel");
        i5_14400F.setNome("Intel Core i5-14400F");
        i5_14400F.setPreassemblato(false);
        i5_14400F.setDisponibilita(true);
        i5_14400F.setQuantitaMagazzino(50);
        i5_14400F.setDescrizione("Mainstream mid-range desktop processor without integrated graphics.");
        i5_14400F.setPrezzo(189.00f); // Real price (approx. current market price)
//        System.out.println(i5_14400F);

        // Intel Core i5-14400T
        ProdottoFisicoBean i5_14400T = new ProdottoFisicoBean();
        i5_14400T.setCodiceIdentificativo("ERFTYSFW4");
        i5_14400T.setArticolo_codiceIdentificativo("ERFTYSFW4"); 
        i5_14400T.setSeriale("20220104BQ0043");
        i5_14400T.setCategoria("Processori");
           i5_14400T.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i5_14400T.setEnteErogatore("Intel");
        i5_14400T.setNome("Intel Core i5-14400T");
        i5_14400T.setPreassemblato(false);
        i5_14400T.setDisponibilita(true);
        i5_14400T.setQuantitaMagazzino(50);
        i5_14400T.setDescrizione("Power-efficient mainstream mid-range desktop processor.");
        i5_14400T.setPrezzo(195.00f); // Estimated price
  //      System.out.println(i5_14400T);

        // Intel Core i5-14401E
        ProdottoFisicoBean i5_14401E = new ProdottoFisicoBean();
        i5_14401E.setCodiceIdentificativo("ADFGD24678");
        i5_14401E.setArticolo_codiceIdentificativo("ADFGD24678");
        i5_14401E.setSeriale("20220104BR0044");
        i5_14401E.setCategoria("Processori");
           i5_14401E.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i5_14401E.setEnteErogatore("Intel");
        i5_14401E.setNome("Intel Core i5-14401E");
        i5_14401E.setPreassemblato(false);
        i5_14401E.setDisponibilita(true);
        i5_14401E.setQuantitaMagazzino(50);
        i5_14401E.setDescrizione("Embedded mainstream mid-range desktop processor.");
        i5_14401E.setPrezzo(215.00f); // Estimated price
   //     System.out.println(i5_14401E);

        // Intel Core i5-14401EF
        ProdottoFisicoBean i5_14401EF = new ProdottoFisicoBean();
        i5_14401EF.setCodiceIdentificativo("67899HGVDX");
        i5_14401EF.setArticolo_codiceIdentificativo("67899HGVDX");
        i5_14401EF.setSeriale("20220104BS0045");
        i5_14401EF.setCategoria("Processori");
           i5_14401EF.setDataUltimaPromozione(new Date(System.currentTimeMillis()));
        i5_14401EF.setEnteErogatore("Intel");
        i5_14401EF.setNome("Intel Core i5-14401EF");
        i5_14401EF.setPreassemblato(false);
        i5_14401EF.setDisponibilita(true);
        i5_14401EF.setQuantitaMagazzino(50);
        i5_14401EF.setDescrizione("Embedded mainstream mid-range desktop processor without integrated graphics.");
        i5_14401EF.setPrezzo(205.00f); // Estimated price
   //     System.out.println(i5_14401EF);

        // Intel Core i5-14401TE
        ProdottoFisicoBean i5_14401TE = new ProdottoFisicoBean();
        i5_14401EF.setCodiceIdentificativo("234DCXSSAX");
        i5_14401TE.setArticolo_codiceIdentificativo("234DCXSSAX"); 
        i5_14401TE.setSeriale("20220104BT0046");
        i5_14401TE.setCategoria("Processori");
           i5_14401TE.setDataUltimaPromozione(new Date(System.currentTimeMillis())); 
        i5_14401TE.setEnteErogatore("Intel");
        i5_14401TE.setNome("Intel Core i5-14401TE");
        i5_14401TE.setPreassemblato(false);
        i5_14401TE.setDisponibilita(true);
        i5_14401TE.setQuantitaMagazzino(50);
        i5_14401TE.setDescrizione("Embedded power-efficient mainstream mid-range desktop processor.");
        i5_14401TE.setPrezzo(190.00f); // Estimated price
   //     System.out.println(i5_14401TE);

        // Intel Core i5-14501E
        ProdottoFisicoBean i5_14501E = new ProdottoFisicoBean();
        i5_14501E.setCodiceIdentificativo("24TGVFDE");
        i5_14501E.setArticolo_codiceIdentificativo("24TGVFDE");
        i5_14501E.setSeriale("20220104BU0047");
        i5_14501E.setCategoria("Processori");
           i5_14501E.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        i5_14501E.setEnteErogatore("Intel");
        i5_14501E.setNome("Intel Core i5-14501E");
        i5_14501E.setPreassemblato(false);
        i5_14501E.setDisponibilita(true);
        i5_14501E.setQuantitaMagazzino(50);
        i5_14501E.setDescrizione("Embedded mid-range desktop processor.");
        i5_14501E.setPrezzo(245.00f); // Estimated price
 //       System.out.println(i5_14501E);

        // Intel Core i5-14501TE
        ProdottoFisicoBean i5_14501TE = new ProdottoFisicoBean();
        i5_14501TE.setCodiceIdentificativo("ASFL3DF4");
        i5_14501TE.setArticolo_codiceIdentificativo("ASFL3DF4"); 
        i5_14501TE.setSeriale("20211104BV0048");
        i5_14501TE.setCategoria("Processori");
           i5_14501TE.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        i5_14501TE.setEnteErogatore("Intel");
        i5_14501TE.setNome("Intel Core i5-14501TE");
        i5_14501TE.setPreassemblato(false);
        i5_14501TE.setDisponibilita(true);
        i5_14501TE.setQuantitaMagazzino(50);
        i5_14501TE.setDescrizione("Embedded power-efficient mid-range desktop processor.");
        i5_14501TE.setPrezzo(230.00f); // Estimated price
  //      System.out.println(i5_14501TE);

        // Intel Core i3-14100
        ProdottoFisicoBean i3_14100 = new ProdottoFisicoBean();
        i3_14100.setCodiceIdentificativo("FGT356");
        i3_14100.setArticolo_codiceIdentificativo("FGT356"); 
        i3_14100.setSeriale("20211104BW0049");
        i3_14100.setCategoria("Processori");
           i3_14100.setDataUltimaPromozione(new Date(System.currentTimeMillis()));    
        i3_14100.setEnteErogatore("Intel");
        i3_14100.setNome("Intel Core i3-14100");
        i3_14100.setPreassemblato(false);
        i3_14100.setDisponibilita(true);
        i3_14100.setQuantitaMagazzino(50);
        i3_14100.setDescrizione("Entry-level desktop processor.");
        i3_14100.setPrezzo(149.00f); // Real price (approx. current market price)
//        System.out.println(i3_14100);

        // Intel Core i3-14100F
        ProdottoFisicoBean i3_14100F = new ProdottoFisicoBean();
        i3_14100F.setCodiceIdentificativo("FSFSVG345");
        i3_14100F.setArticolo_codiceIdentificativo("FSFSVG345"); 
        i3_14100F.setSeriale("20220104BX0050");
        i3_14100F.setCategoria("Processori");
           i3_14100F.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        i3_14100F.setEnteErogatore("Intel");
        i3_14100F.setNome("Intel Core i3-14100F");
        i3_14100F.setPreassemblato(false);
        i3_14100F.setDisponibilita(true);
        i3_14100F.setQuantitaMagazzino(50);
        i3_14100F.setDescrizione("Entry-level desktop processor without integrated graphics.");
        i3_14100F.setPrezzo(129.00f); // Real price (approx. current market price)
    //    System.out.println(i3_14100F);

        // Intel Core i3-14100T
        ProdottoFisicoBean i3_14100T = new ProdottoFisicoBean();
        i3_14100T.setCodiceIdentificativo("FSASDSVG7899");
        i3_14100T.setArticolo_codiceIdentificativo("FSASDSVG7899"); 
        i3_14100T.setSeriale("20220104BY0051");
        i3_14100T.setCategoria("Processori");
           i3_14100T.setDataUltimaPromozione(new Date(System.currentTimeMillis()));    
        i3_14100T.setEnteErogatore("Intel");
        i3_14100T.setNome("Intel Core i3-14100T");
        i3_14100T.setPreassemblato(false);
        i3_14100T.setDisponibilita(true);
        i3_14100T.setQuantitaMagazzino(50);
        i3_14100T.setDescrizione("Power-efficient entry-level desktop processor.");
        i3_14100T.setPrezzo(140.00f); // Estimated price
//        System.out.println(i3_14100T);

        // Intel Core Ultra 9 Processor 285K 24C 24T 5.70 GHz
        ProdottoFisicoBean ultra9_285K = new ProdottoFisicoBean();
        ultra9_285K.setCodiceIdentificativo("FSXCAFD2456");
        ultra9_285K.setArticolo_codiceIdentificativo("FSXCAFD2456"); 
        ultra9_285K.setSeriale("20220104BZ0052");
        ultra9_285K.setCategoria("Processori");
           ultra9_285K.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        ultra9_285K.setEnteErogatore("Intel");
        ultra9_285K.setNome("Intel Core Ultra 9 Processor 285K 24C 24T 5.70 GHz");
        ultra9_285K.setPreassemblato(false);
        ultra9_285K.setDisponibilita(true);
        ultra9_285K.setQuantitaMagazzino(50);
        ultra9_285K.setDescrizione("High-performance unlocked desktop processor for Processori.");
        ultra9_285K.setPrezzo(649.00f); // Estimated price (new platform, high-end)
  //      System.out.println(ultra9_285K);

        // Intel Core Ultra 9 Processor 285T 24C 24T 5.40 GHz
        ProdottoFisicoBean ultra9_285T = new ProdottoFisicoBean();
        ultra9_285T.setCodiceIdentificativo("FXVADV12434");
        ultra9_285T.setArticolo_codiceIdentificativo("FXVADV12434"); 
        ultra9_285T.setSeriale("20220104CA0053");
        ultra9_285T.setCategoria("Processori");
           ultra9_285T.setDataUltimaPromozione(new Date(System.currentTimeMillis()));
        ultra9_285T.setEnteErogatore("Intel");
        ultra9_285T.setNome("Intel Core Ultra 9 Processor 285T 24C 24T 5.40 GHz");
        ultra9_285T.setPreassemblato(false);
        ultra9_285T.setDisponibilita(true);
        ultra9_285T.setQuantitaMagazzino(50);
        ultra9_285T.setDescrizione("Power-efficient high-performance desktop processor for Processori.");
        ultra9_285T.setPrezzo(600.00f); // Estimated price
   //     System.out.println(ultra9_285T);

        // Intel Core Ultra 9 Processor 285 24C 24T 5.60 GHz
        ProdottoFisicoBean ultra9_285 = new ProdottoFisicoBean();
        ultra9_285.setCodiceIdentificativo("FZCVZV1234");
        ultra9_285.setArticolo_codiceIdentificativo("FZCVZV1234"); 
        ultra9_285.setSeriale("20220104CB0054");
        ultra9_285.setCategoria("Processori");
           ultra9_285.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        ultra9_285.setEnteErogatore("Intel");
        ultra9_285.setNome("Intel Core Ultra 9 Processor 285 24C 24T 5.60 GHz");
        ultra9_285.setPreassemblato(false);
        ultra9_285.setDisponibilita(true);
        ultra9_285.setQuantitaMagazzino(50);
        ultra9_285.setDescrizione("High-performance desktop processor for Processori.");
        ultra9_285.setPrezzo(620.00f); // Estimated price
  //      System.out.println(ultra9_285);

        // Intel Core Ultra 7 Processor 265K 20C 20T 5.50 GHz
        ProdottoFisicoBean ultra7_265K = new ProdottoFisicoBean();
        ultra7_265K.setCodiceIdentificativo("FXZVZWTG2345");
        ultra7_265K.setArticolo_codiceIdentificativo("FXZVZWTG2345"); 
        ultra7_265K.setSeriale("20220104CC0055");
        ultra7_265K.setCategoria("Processori");
           ultra7_265K.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        ultra7_265K.setEnteErogatore("Intel");
        ultra7_265K.setNome("Intel Core Ultra 7 Processor 265K 20C 20T 5.50 GHz");
        ultra7_265K.setPreassemblato(false);
        ultra7_265K.setDisponibilita(true);
        ultra7_265K.setQuantitaMagazzino(50);
        ultra7_265K.setDescrizione("High-performance unlocked desktop processor for Processori.");
        ultra7_265K.setPrezzo(489.00f); // Estimated price
 //       System.out.println(ultra7_265K);

        // Intel Core Ultra 7 Processor 265KF 20C 20T 5.50 GHz
        ProdottoFisicoBean ultra7_265KF = new ProdottoFisicoBean();
        ultra7_265KF.setCodiceIdentificativo("FXCVSDV6554");
        ultra7_265KF.setArticolo_codiceIdentificativo("FXCVSDV6554"); 
        ultra7_265KF.setSeriale("20230103CD0056");
        ultra7_265KF.setCategoria("Processori");
           ultra7_265KF.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        ultra7_265KF.setEnteErogatore("Intel");
        ultra7_265KF.setNome("Intel Core Ultra 7 Processor 265KF 20C 20T 5.50 GHz");
        ultra7_265KF.setPreassemblato(false);
        ultra7_265KF.setDisponibilita(true);
        ultra7_265KF.setQuantitaMagazzino(50);
        ultra7_265KF.setDescrizione("High-performance unlocked desktop processor for Processori without integrated graphics.");
        ultra7_265KF.setPrezzo(469.00f); // Estimated price
//        System.out.println(ultra7_265KF);

        // Intel Core Ultra 7 Processor 265T 20C 20T 5.30 GHz
        ProdottoFisicoBean ultra7_265T = new ProdottoFisicoBean();
        ultra7_265T.setCodiceIdentificativo("FBBDDHSGS256");
        ultra7_265T.setArticolo_codiceIdentificativo("FBBDDHSGS256"); 
        ultra7_265T.setSeriale("20230103CE0057");
        ultra7_265T.setCategoria("Processori");
           ultra7_265T.setDataUltimaPromozione(new Date(System.currentTimeMillis()));
        ultra7_265T.setEnteErogatore("Intel");
        ultra7_265T.setNome("Intel Core Ultra 7 Processor 265T 20C 20T 5.30 GHz");
        ultra7_265T.setPreassemblato(false);
        ultra7_265T.setDisponibilita(true);
        ultra7_265T.setQuantitaMagazzino(50);
        ultra7_265T.setDescrizione("Power-efficient high-performance desktop processor for Processori.");
        ultra7_265T.setPrezzo(450.00f); // Estimated price
 //       System.out.println(ultra7_265T);

        // Intel Core Ultra 7 Processor 265F 20C 20T 5.30 GHz
        ProdottoFisicoBean ultra7_265F = new ProdottoFisicoBean();
        ultra7_265F.setCodiceIdentificativo("XDVHRR87766");
        ultra7_265F.setArticolo_codiceIdentificativo("XDVHRR87766"); 
        ultra7_265F.setSeriale("20230103CF0058");
        ultra7_265F.setCategoria("Processori");
           ultra7_265F.setDataUltimaPromozione(new Date(System.currentTimeMillis()));
        ultra7_265F.setEnteErogatore("Intel");
        ultra7_265F.setNome("Intel Core Ultra 7 Processor 265F 20C 20T 5.30 GHz");
        ultra7_265F.setPreassemblato(false);
        ultra7_265F.setDisponibilita(true);
        ultra7_265F.setQuantitaMagazzino(50);
        ultra7_265F.setDescrizione("High-performance desktop processor for Processori without integrated graphics.");
        ultra7_265F.setPrezzo(430.00f); // Estimated price
 //       System.out.println(ultra7_265F);

        // Intel Core Ultra 7 Processor 265 20C 20T 5.30 GHz
        ProdottoFisicoBean ultra7_265 = new ProdottoFisicoBean();
        ultra7_265.setCodiceIdentificativo("ASADGFGD8765");
        ultra7_265.setArticolo_codiceIdentificativo("ASADGFGD8765");
        ultra7_265.setSeriale("20230103CG0059");
        ultra7_265.setCategoria("Processori");
           ultra7_265.setDataUltimaPromozione(new Date(System.currentTimeMillis()));
        ultra7_265.setEnteErogatore("Intel");
        ultra7_265.setNome("Intel Core Ultra 7 Processor 265 20C 20T 5.30 GHz");
        ultra7_265.setPreassemblato(false);
        ultra7_265.setDisponibilita(true);
        ultra7_265.setQuantitaMagazzino(50);
        ultra7_265.setDescrizione("High-performance desktop processor for Processori.");
        ultra7_265.setPrezzo(440.00f); // Estimated price
    //    System.out.println(ultra7_265);

        // Intel Core Ultra 5 Processor 245K 14C 14T 5.20 GHz
        ProdottoFisicoBean ultra5_245K = new ProdottoFisicoBean();
        ultra5_245K.setCodiceIdentificativo("SAFF12XCG");
        ultra5_245K.setArticolo_codiceIdentificativo("SAFF12XCG"); 
        ultra5_245K.setSeriale("20230103CH0060");
        ultra5_245K.setCategoria("Processori");
           ultra5_245K.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        ultra5_245K.setEnteErogatore("Intel");
        ultra5_245K.setNome("Intel Core Ultra 5 Processor 245K 14C 14T 5.20 GHz");
        ultra5_245K.setPreassemblato(false);
        ultra5_245K.setDisponibilita(true);
        ultra5_245K.setQuantitaMagazzino(50);
        ultra5_245K.setDescrizione("Mid-range unlocked desktop processor for Processori.");
        ultra5_245K.setPrezzo(309.00f); // Estimated price
  //      System.out.println(ultra5_245K);

        // Intel Core Ultra 5 Processor 245KF 14C 14T 5.20 GHz
        ProdottoFisicoBean ultra5_245KF = new ProdottoFisicoBean();
        ultra5_245K.setCodiceIdentificativo("ASVATY877");
        ultra5_245KF.setArticolo_codiceIdentificativo("ASVATY877"); 
        ultra5_245KF.setSeriale("20230103CI0061");
        ultra5_245KF.setCategoria("Processori");
           ultra5_245KF.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        ultra5_245KF.setEnteErogatore("Intel");
        ultra5_245KF.setNome("Intel Core Ultra 5 Processor 245KF 14C 14T 5.20 GHz");
        ultra5_245KF.setPreassemblato(false);
        ultra5_245KF.setDisponibilita(true);
        ultra5_245KF.setQuantitaMagazzino(50);
        ultra5_245KF.setDescrizione("Mid-range unlocked desktop processor for Processori without integrated graphics.");
        ultra5_245KF.setPrezzo(290.00f); // Estimated price
 //       System.out.println(ultra5_245KF);

        // Intel Core Ultra 5 Processor 245T 14C 14T 5.10 GHz
        ProdottoFisicoBean ultra5_245T = new ProdottoFisicoBean();
        ultra5_245T.setCodiceIdentificativo("ASCVBHYT8765");
        ultra5_245T.setArticolo_codiceIdentificativo("ASCVBHYT8765"); 
        ultra5_245T.setSeriale("20230103CJ0062");
        ultra5_245T.setCategoria("Processori");
           ultra5_245T.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        ultra5_245T.setEnteErogatore("Intel");
        ultra5_245T.setNome("Intel Core Ultra 5 Processor 245T 14C 14T 5.10 GHz");
        ultra5_245T.setPreassemblato(false);
        ultra5_245T.setDisponibilita(true);
        ultra5_245T.setQuantitaMagazzino(50);
        ultra5_245T.setDescrizione("Power-efficient mid-range desktop processor for Processori.");
        ultra5_245T.setPrezzo(280.00f); // Estimated price
   //    System.out.println(ultra5_245T);

        // Intel Core Ultra 5 Processor 245 14C 14T 5.10 GHz
        ProdottoFisicoBean ultra5_245 = new ProdottoFisicoBean();
        ultra5_245.setCodiceIdentificativo("ASCAVHLKF909877");
        ultra5_245.setArticolo_codiceIdentificativo(""); 
        ultra5_245.setSeriale("20230103CK0063");
        ultra5_245.setCategoria("Processori");
           ultra5_245.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        ultra5_245.setEnteErogatore("Intel");
        ultra5_245.setNome("Intel Core Ultra 5 Processor 245 14C 14T 5.10 GHz");
        ultra5_245.setPreassemblato(false);
        ultra5_245.setDisponibilita(true);
        ultra5_245.setQuantitaMagazzino(50);
        ultra5_245.setDescrizione("Mid-range desktop processor for Processori.");
        ultra5_245.setPrezzo(295.00f); // Estimated price
 //       System.out.println(ultra5_245);

        // Intel Core Ultra 5 Processor 235T 14C 14T 5.00 GHz
        ProdottoFisicoBean ultra5_235T = new ProdottoFisicoBean();
        ultra5_235T.setCodiceIdentificativo("");
        ultra5_235T.setArticolo_codiceIdentificativo(""); 
        ultra5_235T.setSeriale("20221020CL0064");
        ultra5_235T.setCategoria("Processori");
           ultra5_235T.setDataUltimaPromozione(new Date(System.currentTimeMillis()));  
        ultra5_235T.setEnteErogatore("Intel");
        ultra5_235T.setNome("Intel Core Ultra 5 Processor 235T 14C 14T 5.00 GHz");
        ultra5_235T.setPreassemblato(false);
        ultra5_235T.setDisponibilita(true);
        ultra5_235T.setQuantitaMagazzino(50);
        ultra5_235T.setDescrizione("Power-efficient mid-range desktop processor for Processori.");
        ultra5_235T.setPrezzo(270.00f); // Estimated price
  //      System.out.println(ultra5_235T);

        // Intel Core Ultra 5 Processor 235 14C 14T 5.00 GHz
        ProdottoFisicoBean ultra5_235 = new ProdottoFisicoBean();
        ultra5_235.setCodiceIdentificativo("");
        ultra5_235.setArticolo_codiceIdentificativo("");
        ultra5_235.setSeriale("20221020CM0065");
        ultra5_235.setCategoria("Processori");
           ultra5_235.setDataUltimaPromozione(new Date(System.currentTimeMillis())); 
        ultra5_235.setEnteErogatore("Intel");
        ultra5_235.setNome("Intel Core Ultra 5 Processor 235 14C 14T 5.00 GHz");
        ultra5_235.setPreassemblato(false);
        ultra5_235.setDisponibilita(true);
        ultra5_235.setQuantitaMagazzino(50);
        ultra5_235.setDescrizione("Mid-range desktop processor for Processori.");
        ultra5_235.setPrezzo(285.00f); // Estimated price
   //     System.out.println(ultra5_235);

        // Intel Core Ultra 5 Processor 230F 10C 10T 5.00 GHz
        ProdottoFisicoBean ultra5_230F = new ProdottoFisicoBean();
        ultra5_230F.setCodiceIdentificativo("A7B2");
        ultra5_230F.setArticolo_codiceIdentificativo("A7B2");
        ultra5_230F.setSeriale("20221020CN0066");
        ultra5_230F.setCategoria("Processori");
           ultra5_230F.setDataUltimaPromozione(new Date(System.currentTimeMillis()));   
        ultra5_230F.setEnteErogatore("Intel");
        ultra5_230F.setNome("Intel Core Ultra 5 Processor 230F 10C 10T 5.00 GHz");
        ultra5_230F.setPreassemblato(false);
        ultra5_230F.setDisponibilita(true);
        ultra5_230F.setQuantitaMagazzino(50);
        ultra5_230F.setDescrizione("Mid-range desktop processor for Processori without integrated graphics.");
        ultra5_230F.setPrezzo(260.00f); // Estimated price
   //     System.out.println(ultra5_230F);

        // Intel Core Ultra 5 Processor 225T 10C 10T 4.90 GHz
        ProdottoFisicoBean ultra5_225T = new ProdottoFisicoBean();
        ultra5_225T.setCodiceIdentificativo("C9D5");
        ultra5_225T.setArticolo_codiceIdentificativo("C9D5"); 
        ultra5_225T.setSeriale("20221020CO0067");
        ultra5_225T.setCategoria("Processori");
        ultra5_225T.setEnteErogatore("Intel");
        ultra5_225T.setNome("Intel Core Ultra 5 Processor 225T 10C 10T 4.90 GHz");
        ultra5_225T.setPreassemblato(false);
        ultra5_225T.setDisponibilita(true);
        ultra5_225T.setQuantitaMagazzino(50);
        ultra5_225T.setDescrizione("Power-efficient mid-range desktop processor for Processori.");
        ultra5_225T.setPrezzo(250.00f); // Estimated price
//        System.out.println(ultra5_225T);
		
		
		ProdottoFisicoDao dao = new ProdottoFisicoDao();
        try {
        	dao.doSave(i3_14100);
        	dao.doSave(i3_14100F);
        	dao.doSave(i3_14100T);
        	dao.doSave(i5_13600K);
        	dao.doSave(i5_13600KF);
        	dao.doSave(i5_14400);
        	dao.doSave(i5_14400F);
        	dao.doSave(i5_14400T);
        	dao.doSave(ultra9_285T);
        	dao.doSave(ultra9_285K);
        	dao.doSave(ultra9_285);
        	dao.doSave(ultra7_265T);
        	dao.doSave(ultra7_265KF);
        	dao.doSave(ultra7_265K);
        	dao.doSave(ultra5_245K);
        	dao.doSave(ultra5_245T);
        	dao.doSave(ultra5_245);
        	dao.doSave(ultra5_235T);
        	dao.doSave(ultra7_265);
        	dao.doSave(ultra5_235);
        	dao.doSave(ultra5_230F);
        	dao.doSave(ultra5_225T);
        	dao.doSave(i9_14901TE);
        	dao.doSave(i9_14901KE);
        	dao.doSave(i9_14900K);
        	dao.doSave(i9_14900F);
        	dao.doSave(i9_14900);
        	dao.doSave(i9_13900K);
        	dao.doSave(i9_12900TE);
        	dao.doSave(i9_12900T);
        	dao.doSave(i9_12900KS);
        	dao.doSave(i9_12900KF);
        	dao.doSave(i9_12900K);
        	dao.doSave(i9_12900);
        	dao.doSave(i7_14701TE);
        	dao.doSave(i7_14700KF);
        	dao.doSave(i7_14700K);
        	dao.doSave(i7_12700);
        	dao.doSave(i7_12700K);
        	dao.doSave(i5_14500T);
        	dao.doSave(i5_14600T);
        	dao.doSave(i5_14401TE);
        	dao.doSave(i7_14700);
        	dao.doSave(i5_14501TE);
        }catch(SQLException e) {
        	e.printStackTrace();
        }
	}	
}
