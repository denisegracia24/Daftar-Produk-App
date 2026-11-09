package kelas.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;

public class FormController {

    @FXML private TextField txtKode;
    @FXML private TextField txtNama;
    @FXML private ComboBox<String> cbKategori;
    @FXML private TextField txtHarga;

    private Navigator navigator;
    private DataProduk data;

    @FXML
    public void initialize() {
        if (cbKategori != null) {
            cbKategori.getItems().addAll("Makanan", "Minuman");
            cbKategori.setValue("Makanan");
        }
    }

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;

        if (data != null && txtKode != null) {
            txtKode.setText(data.generateKodeOtomatis());
            txtKode.setEditable(false);
            txtKode.setStyle("-fx-background-color: #ecf0f1;");
        }
    }

    @FXML
    private void handleSimpan() {
        try {
            String kode = txtKode.getText();
            String nama = txtNama.getText();
            String kategori = cbKategori.getValue();
            
            if (txtHarga.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Harga tidak boleh kosong!");
            }
            double harga = Double.parseDouble(txtHarga.getText());

            Produk produkBaru = new Produk(kode, nama, kategori, harga);
            
            data.tambahProduk(produkBaru);

            navigator.bukaDaftar();

        } catch (NumberFormatException e) {
            tampilkanAlert("Error", "Format harga harus berupa angka!");
        } catch (IllegalArgumentException e) {
            tampilkanAlert("Validasi Gagal", e.getMessage());
        } catch (Exception e) {
            tampilkanAlert("Error", "Terjadi kesalahan: " + e.getMessage());
        }
    }

    @FXML
    private void handleBatal() throws IOException {
        navigator.bukaDaftar();
    }

    private void tampilkanAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}