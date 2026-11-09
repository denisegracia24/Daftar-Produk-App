package kelas.model;

public class Produk {
    private String kodeProduk;
    private String namaProduk;
    private String kategori;
    private double harga;

    public Produk(String kodeProduk, String namaProduk, String kategori, double harga) {
        setKodeProduk(kodeProduk);
        setNamaProduk(namaProduk);
        setKategori(kategori);
        setHarga(harga);
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        if (namaProduk == null || namaProduk.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong!");
        }
        this.namaProduk = namaProduk;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        if (harga <= 0) {
            throw new IllegalArgumentException("Harga harus lebih dari nol!");
        }
        this.harga = harga;
    }
}