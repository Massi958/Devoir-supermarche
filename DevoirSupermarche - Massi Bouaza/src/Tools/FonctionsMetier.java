/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entity.Employe;
import Entity.Rayon;
import Entity.Secteur;
import Entity.Travailler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jbuffeteau
 */
public class FonctionsMetier
{
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection cnx;

    public FonctionsMetier()
    {
        cnx = ConnexionBDD.getCnx();
    }
    
    public ArrayList<Secteur> GetAllSecteurs()
    {
        ArrayList<Secteur> lesSecteurs = new ArrayList<>();
        try {
            
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select nomS,numS\n" +"from secteur");
            ResultSet rs = ps.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lesSecteurs;
    }
    
    public ArrayList<Employe> GetAllEmployes()
    { 
        ArrayList<Employe> lesEmploye = new ArrayList<>();
        try {
            
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select prenomE\n" +"from employe");
            ResultSet rs = ps.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lesEmploye;
        
       
    }
    
    public ArrayList<Rayon> GetAllRayonsByIdsecteur(int numSecteur)
    {
        ArrayList<Rayon> lesRayon = new ArrayList<>();
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select nomR,numR from rayon where numSecteur="+numSecteur);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Rayon ray = new Rayon(rs.getInt("numR"), rs.getString("nomR"));
                lesRayon.add(ray);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesRayon;    
    }
    
    public ArrayList<Travailler> GetAllTravailler(int numRayon)
    {
        ArrayList<Travailler> lesTrav = new ArrayList<>();
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select codeE,date,temps,prenomE from travailler,employe where numE=codeE and codeR="+numRayon);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Travailler trav = new Travailler(rs.getString("unEmploye"), rs.getString("nom"),rs.getInt("temps"));
                lesTrav.add(trav);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesTrav;      
    }
    
    public int GetIdEmployeByNom(String nomEmploye)
    {
       int id = 0;
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select numE from employe where prenomE="+"nomEmploye");
            ResultSet rs = ps.executeQuery();
            rs.next();
            id = rs.getInt("numE");
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
        
    }
    
    public int TotalHeuresRayon(int numRayon)
    { 
        int tempstotal=0;
        try {
           
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("SELECT SUM(temps) FROM travailler WHERE codeR=1"+numRayon);
            ResultSet rs = ps.executeQuery();
            rs.next();
            tempstotal = rs.getInt("temps");
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempstotal;
    }
    
    public void ModifierTemps(int codeEmploye, int CodeRayon, String uneDate,int nouveauTemps)
    {
        
    }
    
    public void InsererTemps(int codeEmploye, int CodeRayon,int nouveauTemps)
    {
        
    }
}
