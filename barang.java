import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class barang implements penjualan {

    String namabrg, idkasir, namakasir, notrans, kasirrr, ukurannya,nota;
    String ukurn[] = {"kecil", "menengah", "besar"};
    
    int ukur,jumlaa;    //jumlaa adalah jumlah barang yang diambil dari database
    int hargabrg, jumlahbrg;    //untuk diinputkan ke database
    int jumlablanja;//jumlah barang yang diinginkan dan juga untuk menghitung diskon
    String kodebarang;//kode barang 

    int subttl,diskonn,total;

    //constructor
    public barang(int hargabrg, int jumlahbrg) {
        this.hargabrg = hargabrg;
        this.jumlahbrg = jumlahbrg;
    }

    Scanner brg = new Scanner(System.in);

    public void kasir(){
        System.out.print("\nMasukkan ID kasir : ");
        idkasir = brg.nextLine();
        System.out.print("Masukkan nama kasir : ");
        namakasir = brg.nextLine();
        namakasir = namakasir.toUpperCase(); //method string
        kasirrr = idkasir +"-".concat(namakasir);
    }
    
    //method date
    public void notransaksi() { ;
        LocalDateTime notrans = LocalDateTime.now();
        nota = notrans.toString();
        // System.out.println("Nomor transaksi : " + notrans + "\n");
    }

    public void namabarang() {
        System.out.print("\nNama barang : ");
        this.namabrg = brg.nextLine();
        if(namabrg.isEmpty()){
            System.out.println("isikan nama barang anda!!");
            System.exit(0);}
        //System.out.println("Nama barang : " + namabrg);
    }

    public void ukuran() {
        System.out.print("Ukuran(0->kecil | 1->menengah | 2-besar) : ");
        this.ukur = brg.nextInt();
        ukurannya = ukurn[ukur];
        boolean henti=false;
        //exception
        try {
            System.out.println("ukuran " + ukur + " adalah " + ukurannya + "\n");}
        catch (Exception e){
            // System.err.println(e);
            henti=true;
            System.out.println("index yang dimasukkan tidak sesuai!1!1!");
            if(henti){System.exit(0);};
        }
    }

    public void hargabarang() {
        System.out.print("Harga : ");
        this.hargabrg = brg.nextInt(); 
        //System.out.println("Harga : " + hargabrg);       
    }

    public void jumlah() {
        System.out.print("Jumlah : ");
        this.jumlahbrg = brg.nextInt();
        //System.out.println("Jumlah : " + jumlahbrg);       
    }

    public void kodebarang(){
        System.out.println("Masukkan kode Barang : ");
        this.kodebarang = brg.next();
    }

    public int subtotal() {        
        return 0;
    }

    public int diskon() {        
        return 0;
    }

    public int totalharga() {        
        return 0;
    }
    public void tampil(){};
    
}
