/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
//import java.util.List;
//import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author USER
 */

@Path("AdminController")
public class AdminController {
    koneksi con = new koneksi();
    java.sql.Statement st;
    java.sql.ResultSet rs;
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(usergs data) throws ClassNotFoundException {
        String result = "0";
        try {
            java.sql.Connection conn = (Connection) con.configDB();
            st = conn.createStatement();
        
            String a = "SELECT * FROM tbl_user WHERE level='admin' AND nama = '" + data.getNama()+ "' AND password = '" + data.getPassword()+ "'";
            rs = st.executeQuery(a);
            while (rs.next()) {            
                result = "1";
            }
        } catch (Exception e) {
            result = "Error! \n" + e.toString();
        }
        
        return Response.status(201).entity(result).build();
    }
    
    
    @GET
    @Path("/getdatacostumer")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<testModel> getData() throws ClassNotFoundException {
        ArrayList<testModel> tmn = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbl_costumer";
            java.sql.Connection conn = (Connection) con.configDB();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
          
            while (rs.next()) {
                testModel tm = new testModel();
                tm.setId_cos(rs.getInt(1));
                tm.setName_cos(rs.getString(2));
                tm.setAlamat_cos(rs.getString(3));
                tm.setBirthdate_cos(rs.getDate(4));
                tm.setNohp_cos(rs.getString(5));
                tm.setKelamin_cos(rs.getString(6));
                tm.setCekin_cos(rs.getDate(7));
                tm.setCekout_cos(rs.getDate(8));
                tm.setType_cos(rs.getString(9));
                tm.setKamar_cos(rs.getString(10));
                tm.setPrice_cos(rs.getString(11));
                tmn.add(tm);
            }
            st.close();
            conn.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tmn;
    }
    //data tbl_user
    @GET
    @Path("/getdatauser")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<usergs> getDataUser() throws ClassNotFoundException {
        ArrayList<usergs> tmn = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbl_user";
            java.sql.Connection conn = (Connection) con.configDB();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
          
            while (rs.next()) {
                usergs tm = new usergs();
                tm.setId_user(rs.getInt(1));
                tm.setNama(rs.getString(2));
                tm.setPassword(rs.getString(3));
                tm.setLevel(rs.getString(4));
                tmn.add(tm);
            }
            st.close();
            conn.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tmn;
    }
    
    
     @POST
    @Path("/updateuser")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateuser(usergs model) {
        try {
            String sql = "UPDATE tbl_user SET nama='" + model.getNama()+ "', password = '" + model.getPassword()+ "', level = '" + model.getLevel()+ "' "
                    + "where id_user='"+model.getId_user()+"'";
            java.sql.Connection conn = (Connection) con.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String result =   model.getNama()  + model.getPassword();
         System.out.println(model.getId_user());
        return result;
    }
    
    
    @POST
    @Path("/updatecostumer")
    @Produces(MediaType.APPLICATION_JSON)
    public String updatecostumer(testModel model) {
        try {
            String sql = "UPDATE tbl_costumer SET id_cos='" + model.getId_cos()+ "', name_cos = '" + model.getName_cos()+ "', alamat_cos = '" + model.getAlamat_cos()
                    + "', birthdate_cos = '" + model.getBirthdate_cos() + "', nohp_cos = '" + model.getNohp_cos() + "', kelamin_cos = '" + model.getKelamin_cos()
                    + "', cekin_cos = '" + model.getCekin_cos() + "', cekout_cos = '" + model.getCekout_cos()+ "', Type_cos = '" + model.getType_cos()+ "', kamar_cos = '" + model.getKamar_cos()+ "',price_cos = '" + model.getPrice_cos()+ "' "
                    + "where id_cos='" + model.getId_cos()+"'";
            java.sql.Connection conn = (Connection) con.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String result =  model.getName_cos() + model.getAlamat_cos() + 
                model.getBirthdate_cos() + model.getNohp_cos() + model.getKelamin_cos() + model.getCekin_cos() + model.getCekout_cos() + model.getType_cos()+ model.getKamar_cos()+ model.getPrice_cos();
         System.out.println(model.getId_cos());
        return result;
    }
    
    

    @POST
    @Path("/inputuser")
    @Produces(MediaType.APPLICATION_JSON)
    public String inputuser(usergs model) {
        try {
            String sql = "INSERT INTO tbl_user(nama,password,level) VALUES ('" + model.getNama()+ "','" + model.getPassword()+ "','" + model.getLevel()+ "')";
            java.sql.Connection conn = (Connection) con.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String result = model.getNama() + model.getPassword();
        return result;
    }
    @POST
    @Path("/inputcostumer")
    @Produces(MediaType.APPLICATION_JSON)
    public String inputcostumer(testModel model) {
        try {
            String sql = "INSERT INTO tbl_costumer(id_cos,name_cos,alamat_cos,birthdate_cos, nohp_cos, kelamin_cos, cekin_cos, cekout_cos,type_cos,kamar_cos,price_cos) VALUES "
                    + "('" + model.getId_cos()+ "','" + model.getName_cos()+ "','" + model.getAlamat_cos()+ 
                     "','" + model.getBirthdate_cos()+ "','" + model.getNohp_cos()+ "','" + model.getKelamin_cos()+
                     "','" + model.getCekin_cos()+"','" + model.getCekout_cos()+ "', '"+ model.getType_cos()+
                    "','"+ model.getKamar_cos()+ "','"+ model.getPrice_cos()+ "')";
            java.sql.Connection conn = (Connection) con.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String result = model.getId_cos() +  model.getName_cos() + model.getAlamat_cos() + 
                model.getBirthdate_cos() + model.getNohp_cos() + model.getKelamin_cos() + model.getCekin_cos() + model.getCekout_cos() + 
                model.getType_cos()+ model.getKamar_cos()+ model.getPrice_cos();
        return result;
    }
    
    
    
    @POST
    @Path("/deleteuser")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteuser(usergs model) {
        try {
            String sql = "DELETE FROM tbl_user where id_user='"+model.getId_user()+"'";
            java.sql.Connection conn = (Connection) con.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        String result = ""+ model.getId_user();
        return result;
    }
    @POST
    @Path("/deletecostumer")
    @Produces({MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
    public int deletecostumer(testModel model) {
        try {
            String sql = "DELETE FROM tbl_costumer where id_cos='"+(model.getId_cos())+"'";
            java.sql.Connection conn = (Connection) con.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        int result = model.getId_cos();
        return result;
    }
    
}