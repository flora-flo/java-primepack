/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Maladie;
import entites.Operation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.MaladieService;
import services.OperationService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Nessim Melliti
 */
public class GestionOperationController implements Initializable {

    OperationService service;
    ObservableList<Operation> list = FXCollections.observableArrayList();
    Operation operation;
    GestionOperationController controller;
    @FXML
    private TableView<Operation> TableView;
    @FXML
    private TableColumn<Operation, String> nomMladieCell;
    @FXML
    private TableColumn<Operation, String> animaleCell;
    @FXML
    private TableColumn<Operation, String> dateOperationCell;
    @FXML
    private TableColumn<Operation, String> typeOperationCell;
    @FXML
    private TableColumn<Operation, String> nomMedcinCell;
    @FXML
    private TableColumn<Operation, String> coutOperationCell;
    @FXML
    private TableColumn<Operation, String> noteOperation;
    @FXML
    private TableColumn<Operation, String> ActionCell;
    @FXML
    private Button ButtonAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = this;
        service = new OperationService();
        LoadData();
    }

    public void refreshTable() {

        list.clear();
        list.addAll(service.getAll());
        TableView.setItems(list);
    }

    private void LoadData() {

        refreshTable();
        animaleCell.setCellValueFactory(new PropertyValueFactory<>("animale_id"));
        nomMladieCell.setCellValueFactory(cellData
                -> {
            return new SimpleStringProperty(cellData.getValue().getMaladie().getNom());
        });
        dateOperationCell.setCellValueFactory(new PropertyValueFactory<>("dateOperation"));
        typeOperationCell.setCellValueFactory(new PropertyValueFactory<>("typeOperation"));
        nomMedcinCell.setCellValueFactory(new PropertyValueFactory<>("nomMedecin"));
        coutOperationCell.setCellValueFactory(new PropertyValueFactory<>("coutOperation"));
        noteOperation.setCellValueFactory(new PropertyValueFactory<>("noteOperation"));

        ActionCell.setCellFactory(createActionButton());

    }

    private Callback<TableColumn<Operation, String>, TableCell<Operation, String>> createActionButton() {
        Callback<TableColumn<Operation, String>, TableCell<Operation, String>> cellFoctory = (TableColumn<Operation, String> param) -> {
            // make cell containing buttons
            final TableCell<Operation, String> cell = new TableCell<Operation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView logoIcon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_CIRCLE);
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirmation de suppression ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                            alert.showAndWait();

                            if (alert.getResult() == ButtonType.YES) {
                                operation = TableView.getSelectionModel().getSelectedItem();
                                service.delete(operation);
                                refreshTable();
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {

                                operation = TableView.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUpdateOperation.fxml"));

                                Parent root;
                                root = loader.load();

                                AddUpdateOperationController controllera = loader.getController();
                                controllera.initializeController(controller);
                                controllera.initializeTextField(operation);
                                controllera.setWindowType("Update");
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(GestionOperationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                        logoIcon.setOnMouseClicked((MouseEvent event) -> {

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(logoIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        return cellFoctory;
    }

    @FXML
    private void openAjoutDialog(ActionEvent event) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("AddUpdateOperation.fxml"));
            Parent root = fxml.load();
            AddUpdateOperationController controller = fxml.getController();
            controller.setWindowType("Ajout");
            controller.initializeController(this);
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();

            Scene scene = new Scene(root, 600.0, Math.min(400.0, screenBounds.getHeight()));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionMaladieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void exportPDF(ActionEvent event) {
        try {
            String myDocuments = null;

            String absolutePath = new File("").getAbsolutePath();
            String file_path = absolutePath + "\\src\\GUI\\PDF.pdf";
            List<Operation> list = new ArrayList<>(service.getAll());

            int firstRow = 700;
            PdfReader pdfReader = new PdfReader(file_path);

            PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(absolutePath + "\\src\\GUI\\pdfmod.pdf"));
            BaseFont baseFont = BaseFont.createFont(
                    BaseFont.TIMES_ROMAN,
                    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

            for (Operation o : list) {

                PdfContentByte pageContentByte
                        = pdfStamper.getOverContent(1);

                pageContentByte.beginText();
                pageContentByte.setFontAndSize(baseFont, 11);
                pageContentByte.setTextMatrix(80, firstRow);
                pageContentByte.showText(o.getNomMedecin() + "");
                pageContentByte.endText();

                pageContentByte.beginText();
                pageContentByte.setFontAndSize(baseFont, 11);
                pageContentByte.setTextMatrix(200, firstRow);
                pageContentByte.showText(o.getTypeOperation() + "");
                pageContentByte.endText();

                pageContentByte.beginText();
                pageContentByte.setFontAndSize(baseFont, 11);
                pageContentByte.setTextMatrix(295, firstRow);
                pageContentByte.showText(String.format("%.3f", o.getCoutOperation()) + " DT");
                pageContentByte.endText();

                pageContentByte.beginText();
                pageContentByte.setFontAndSize(baseFont, 11);
                pageContentByte.setTextMatrix(360, firstRow);
                pageContentByte.showText(o.getDateOperation() == null ? "" : o.getDateOperation().toLocalDateTime().toLocalDate() + "");
                pageContentByte.endText();

                pageContentByte.beginText();
                pageContentByte.setFontAndSize(baseFont, 11);
                pageContentByte.setTextMatrix(450, firstRow);
                pageContentByte.showText(o.getMaladie().getNom());
                pageContentByte.endText();
                firstRow = firstRow - 18;

            }

            pdfStamper.close();

            File file = new File(absolutePath + "\\src\\GUI\\pdfmod.pdf");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(GestionOperationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GestionOperationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
