package kelas.controller;

import java.io.IOException;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;

public class DaftarController {

    @FXML private TableView<Produk> tabelProduk;
    @FXML private TableColumn<Produk, String> colKode;
    @FXML private TableColumn<Produk, String> colNama;
    @FXML private TableColumn<Produk, String> colKategori;
    @FXML private TableColumn<Produk, Double> colHarga;
    @FXML private TextField txtCari;

    private Navigator navigator;
    private DataProduk data;

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
        
        colKode.setCellValueFactory(new PropertyValueFactory<>("kodeProduk"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaProduk"));
        colKategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));

        tabelProduk.setItems(data.getListProduk());
    }

    @FXML
    private void handleCari() {
        String keyword = txtCari.getText();
        ObservableList<Produk> hasil = data.cariProduk(keyword);
        tabelProduk.setItems(hasil);
    }

    @FXML
    private void handleTambahBaru() throws IOException {
        navigator.bukaForm();
    }

    @FXML
    private void handleHapus() {
        Produk terpilih = tabelProduk.getSelectionModel().getSelectedItem();
        if (terpilih != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi Hapus");
            alert.setHeaderText(null);
            alert.setContentText("Apakah kamu yakin ingin menghapus produk: " + terpilih.getNamaProduk() + "?");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                data.getListProduk().remove(terpilih);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Peringatan");
            alert.setHeaderText(null);
            alert.setContentText("Silakan pilih produk di tabel yang ingin dihapus!");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleLihatDetail() throws IOException {
        Produk terpilih = tabelProduk.getSelectionModel().getSelectedItem();
        if (terpilih != null) {
            navigator.bukaDetail(terpilih);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Peringatan");
            alert.setHeaderText(null);
            alert.setContentText("Silakan pilih produk yang ingin dilihat detailnya di tabel!");
            alert.showAndWait();
        }
    }
}