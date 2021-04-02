/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author USER
 */
import java.sql.Connection;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;

@Path("ClientController")
public class ClientController {

    koneksi con = new koneksi();
    java.sql.Statement st;
    java.sql.ResultSet rs;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public String login(usergs data) throws ClassNotFoundException {
        String result = "0";
        try {
            java.sql.Connection conn = (Connection) con.configDB();
            st = conn.createStatement();

            String a = "SELECT * FROM tbl_user WHERE level='client' AND nama = '" + data.getNama()
                    + "' AND password = '" + data.getPassword() + "'";
            rs = st.executeQuery(a);
            while (rs.next()) {
                result = rs.getString("id_user");
            }
        } catch (Exception e) {
            result = "Tidak ada";
        }
        return result;
    }

   

    @GET
    @Path("/getdata")
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

   
    
}


