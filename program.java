import java.util.Scanner;
import java.io.IOException;
import java.sql.*;


public class program {
    
    public static void main(String[] args) throws SQLException, IOException {
        
        //string koneksi
        Connection conn;
        String url = "jdbc:mysql://localhost:3306/minimarketamin";
        
        int pilihann;
        //try catch exception
        try (Scanner terimaInput = new Scanner (System.in)) {
            boolean menu=true;
            
            //constructor
            transaksi trs = new transaksi(0, 0);

            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
            	conn = DriverManager.getConnection(url,"root","");
            	System.out.println("Class Driver ditemukan");
            	
                //kasir
                trs.kasir();

                //loop perulangan
                while (menu){
                    System.out.println("\n------------------");
                    System.out.println("    Minimarket Amin");
                    System.out.println("------------------");
                    System.out.println("1. Lihat Data");
                    System.out.println("2. Tambah barang");
                    System.out.println("3. (update data)");
                    System.out.println("4. Hapus Data");
                    System.out.println("5. Cari Data");
                    System.out.println("6. Beli barang");
                    System.out.println("7. Selesai\n");

                    System.out.print("\nPilihan anda (1/2/3/4/5/6/7): ");
                    pilihann = terimaInput.nextInt();

                    //percabangan
                    switch (pilihann) {
                        case 1:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("---LIHAT DATA---");
                        trs.view();
                        break;
                        case 2:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        trs.save();
                        break;
                        case 3:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        trs.update();
                        break;
                        case 4:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        trs.delete();
                        break;
                        case 5:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        trs.search();
                        break;
                        case 6:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                            trs.belibarang();
                            break;
                        case 7:
                            menu = false;
                            break;
                        default:
                            System.err.println("\nInput tidak ditemukan\nSilakan pilih [1-7]");
                            break;
                    }
                }

            }
            catch(ClassNotFoundException ex) {
                System.err.println("Driver Error");
                System.exit(0);
            }
            //exception
            catch(SQLException e){
                System.err.println("Tidak berhasil koneksi");
                // System.err.println(e.getSQLState());
                // System.err.println(e.getStackTrace());
                System.err.println(e.getMessage());
            }
        }
                
        System.out.println("\nSelesai\n");

    }
}
