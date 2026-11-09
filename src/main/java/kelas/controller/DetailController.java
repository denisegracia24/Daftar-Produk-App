package kelas.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import kelas.Navigator;
import kelas.model.Produk;

public class DetailController {

    @FXML
    private Label lblKode;
    @FXML
    private Label lblNama;
    @FXML
    private Label lblKategori;
    @FXML
    private Label lblHarga;

    private Navigator navigator;
    private Produk produk;

    public void setData(Navigator navigator, Produk produk) {
        this.navigator = navigator;
        this.produk = produk;

        if (produk != null) {
            lblKode.setText(produk.getKodeProduk());
            lblNama.setText(produk.getNamaProduk());
            lblKategori.setText(produk.getKategori());
            lblHarga.setText(String.valueOf(produk.getHarga()));
        }
    }

    @FXML
    private void handleKembali() throws IOException {
        navigator.bukaDaftar();
    }
}