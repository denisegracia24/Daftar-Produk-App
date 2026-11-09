package kelas.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kelas.model.Produk;

public class DataProduk {
    private ObservableList<Produk> listProduk = FXCollections.observableArrayList();

    public ObservableList<Produk> getListProduk() {
        return listProduk;
    }

    public void tambahProduk(Produk produk) {
        listProduk.add(produk);
    }

    public String generateKodeOtomatis() {
        int nomorBerikutnya = listProduk.size() + 1;
        return String.format("M%03d", nomorBerikutnya);
    }

    public ObservableList<Produk> cariProduk(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return listProduk;
        }
        
        ObservableList<Produk> hasilPencarian = FXCollections.observableArrayList();
        String key = keyword.toLowerCase().trim();
        
        for (Produk p : listProduk) {
            String hargaStr = String.valueOf(p.getHarga());
            
            if (p.getKodeProduk().toLowerCase().startsWith(key) ||
                p.getNamaProduk().toLowerCase().contains(key) || 
                p.getKategori().toLowerCase().startsWith(key) ||
                hargaStr.equals(key) || hargaStr.startsWith(key)) {
                
                hasilPencarian.add(p);
            }
        }
        return hasilPencarian;
    }
}