import java.util.InputMismatchException;
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

public class transaksi extends barang {

    static Connection conn;
    String urll = "jdbc:mysql://localhost:3306/minimarketamin";

    Scanner inputan = new Scanner(System.in);

    // constructor
    public transaksi(int hargabrg, int jumlahbrg) {
        super(hargabrg, jumlahbrg);
    }

    @Override
    public int subtotal() {
        // operasi matematika
        subttl = hargabrg * jumlablanja;
        // System.out.println("\nSubtotal : " + subttl);
        return subttl;
    }

    @Override
    // percabangan dan proses matematika
    public int diskon() {
        if (subttl > 25000 && subttl <= 50000) {
            diskonn = 500;
        } else if (subttl > 50000 && subttl <= 75000) {
            diskonn = 1000;
        } else if (subttl > 75000 && subttl <= 100000) {
            diskonn = 2000;
        } else if (subttl > 100000) {
            diskonn = 10 * subttl / 100;
        } else {
            diskonn = 0;
        }
        return diskonn;
    }

    @Override
    public int totalharga() {
        total = subttl - diskonn;
        return total;
    }

    @Override
    public void tampil() {

        System.out.println("\nSubtotal : " + subttl);
        System.out.println("Diskon yang didapat : " + diskonn);
        System.out.println("\nTotal harga: " + total);

    }

    // -------------CRUD-------------

    public void view() throws SQLException { // vieeewwww

        System.out.println("Daftar isi seluruh minimarket");
        String sql = "SELECT * FROM minimarket";
        conn = DriverManager.getConnection(urll, "root", "");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        // perulangan
        while (result.next()) {
            System.out.print("\nNomor barang\t: ");
            System.out.print(result.getString("nobarang"));

            System.out.print("\nNama barang\t: ");
            System.out.print(result.getString("namabarang"));

            System.out.print("\nUkuran barang\t: ");
            System.out.print(result.getString("ukuranbarang"));

            System.out.print("\nHarga\t\t: Rp.");
            System.out.print(result.getInt("harga"));

            System.out.print("\nJumlah barang\t: ");
            System.out.println(result.getInt("jumlah"));

        }
    }

    public void save() throws SQLException {// menyimpan data
        System.out.println("---SIMPAN DATA---");
        try {

            namabarang();
            ukuran();
            hargabarang();
            jumlah();
            kodebarang();

            String sql = "INSERT INTO minimarket (nobarang, namabarang, ukuranbarang, harga, jumlah) VALUES ('"
                    + kodebarang + "','" + namabrg + "','" + ukurannya + "','" + hargabrg + "' ,'" + jumlahbrg + "')";

            conn = DriverManager.getConnection(urll, "root", "");
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil input data!!");
        }

        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error!! masukkan indeks ukuran dengan benar");
        }

        catch (InputMismatchException e) {
            System.err.println("Input pilihan dengan angka saja");
        }

    }

    public void delete() throws SQLException {
        System.out.println("---HAPUS DATA---");
        view();
        try {
            System.out.print("Masukkan nomor barang yang akan dihapus : ");
            String keyword = inputan.next();

            String sql = "DELETE FROM minimarket WHERE nobarang = '" + keyword + "'";

            conn = DriverManager.getConnection(urll, "root", "");
            Statement statement = conn.createStatement();

            if (statement.executeUpdate(sql) > 0) {
                System.out.println("Berhasil menghapus data minimarket (Nomor " + keyword + ")");
            }
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan dalam menghapus data");
        } catch (Exception e) {
            System.out.println("masukan data yang benar");
        }
    }

    public void search() throws SQLException {
        System.out.println("---CARI DATA---");
        System.out.print("Masukkan Nama barang yang ingin dilihat : ");
        String keyword = inputan.nextLine();

        String sql = "SELECT * FROM minimarket WHERE namabarang LIKE '%" + keyword + "%'";
        conn = DriverManager.getConnection(urll, "root", "");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            System.out.print("\nNo barang\t: ");
            System.out.print(result.getString("nobarang"));
            System.out.print("\nNama barang\t: ");
            System.out.print(result.getString("namabarang"));
            System.out.print("\nUkuran barang\t: ");
            System.out.print(result.getString("ukuranbarang"));
            System.out.print("\nHarga\t\t: ");
            System.out.print(result.getInt("harga"));
            System.out.print("\nJumlah\t\t: ");
            System.out.println(result.getInt("jumlah"));
        }

    }

    public void update() throws SQLException {
        System.out.println("---UPDATE DATA---");
        view();
        try {
            System.out.print("\nMasukkan nomor barang barang yang hendak diubah: ");
            String keywordd = inputan.next();

            String sql = "SELECT * FROM minimarket WHERE nobarang = '" + keywordd + "'";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next()) {
                // tambah stok
                System.out.print("\nJumlah Baru \t: ");
                Integer tambahan = inputan.nextInt();
                int tambahan2;
                int jumlahlama;
                jumlahlama = result.getInt("jumlah");
                tambahan2 = jumlahlama + tambahan;
                // Integer tambahanbrg = jumlahbrg
                System.out.print("\n");
                sql = "UPDATE minimarket SET jumlah='" + tambahan2 + "' WHERE nobarang='" + keywordd + "'";

                // conn = DriverManager.getConnection(urll,"root","");
                // Statement statementt = conn.createStatement();
                // statementt.execute(sql);
                // System.out.println("Berhasil memperbaharui data transaksi (Nomor
                // "+keywordd+")");

                if (statement.executeUpdate(sql) > 0) {
                    System.out.println("Berhasil memperbaharui data barang (Nomor " + keywordd + ")");
                }
            }
            statement.close();
        }

        catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data barang");
            System.err.println(e.getMessage());
        }

    }

    public void belibarang() throws SQLException { // beli barang menggunakan konsep array list
        view();
        notransaksi();

        String pilihbarang;

        try {
            System.out.print("\nPilih nomor barang yang akan di beli : ");
            pilihbarang = inputan.next();

            String sql = "SELECT * FROM minimarket WHERE nobarang = '" + pilihbarang + "'";
            conn = DriverManager.getConnection(urll, "root", "");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            System.out.println("\n----------------");
            System.out.println("   KONFIRMASI");
            System.out.println("----------------\n");
            System.out.println("Kasir : " + kasirrr);
            System.out.println("\nAnda akan membeli barang berikut");

            // collection framework
            ArrayList<String> barang = new ArrayList<String>();

            while (result.next()) {
                barang.add(" Nomor transaksi: " + nota);
                barang.add(" Nomor barang   : " + (result.getString("nobarang")));
                barang.add(" Nama barang    : " + (result.getString("namabarang")));
                barang.add(" ukuran barang  : " + (result.getString("ukuranbarang")));
                // method string
                barang.add(" Harga          : " + Integer.toString(result.getInt("harga")));
                ukurannya = result.getString("ukuranbarang");
                hargabrg = result.getInt("harga");
                barang.add(" Stok tersedia  : " + Integer.toString(result.getInt("jumlah")));
                jumlaa = result.getInt("jumlah");
            }

            // pengulangan
            for (String i : barang) {
                System.out.println(i);
            }

            System.out.print("\nMasukkan banyak barang yang anda akan dibeli: ");
            jumlablanja = inputan.nextInt();

            if (jumlablanja > jumlaa) {
                System.out.println("/nJumlah yang anda masukkan melebihi stok!");
            } else {
                // System.out.println("Konfirmasi ulang");
                subtotal();
                diskon();
                totalharga();
                tampil();

                System.out.print("\nYakin ingin membeli barang ini?? (yes/n) : ");
                String konfir = inputan.next();
                // System.out.println(konfir);
                if (konfir.equals("yes")) {
                    jumlaa = jumlaa - jumlablanja;
                    sql = "UPDATE minimarket SET jumlah='" + jumlaa + "' WHERE nobarang='" + pilihbarang + "'";

                    if (statement.executeUpdate(sql) > 0) {
                        System.out.println("\nPembelian barang berhasil (Nomor " + pilihbarang + ")");
                    }
                } else {
                    System.out.println("Pembelian dibatalkan!!");
                }
            }

            barang.clear();

        } catch (Exception e) {
            System.err.println("Terjadi kesalahan");
            System.err.println(e.getMessage());
        }

    }

}
