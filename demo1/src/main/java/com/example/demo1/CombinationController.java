package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.demo1.LoginController.menu;

public class CombinationController {
    @FXML
    private Label Stage1Name;

    @FXML
    private Label Stage2Name;

    @FXML
    private Label Stage3Name;

    @FXML
    private Label Stage4Name;

    @FXML
    private Label Stage5Name;

    @FXML
    private Label Stage1CoD;

    @FXML
    private Label Stage1BoD;

    @FXML
    private Label Stage1Tss;

    @FXML
    private Label Stage2CoD;

    @FXML
    private Label Stage2Bod;

    @FXML
    private Label Stage2Tss;

    @FXML
    private Label Stage3CoD;

    @FXML
    private Label Stage3BoD;

    @FXML
    private Label Stage3Tss;

    @FXML
    private Label Stage4CoD;

    @FXML
    private Label Stage4BoD;

    @FXML
    private Label Stage4Tss;

    @FXML
    private Label Stage5CoD;

    @FXML
    private Label Stage5BoD;

    @FXML
    private Label Stage5Tss;

    @FXML
    private Label BestFinalCOD;

    @FXML
    private Label BestFinalBOD;

    @FXML
    private Label BestFinalTss;

    @FXML
    private Label BestFinalCOST;

    @FXML
    private Label CurrentFinalCOD;

    @FXML
    private Label CurrentFinalBOD;

    @FXML
    private Label CurrentFinalTss;

    @FXML
    private Label CurrentFinalCOST;

    @FXML
    private Label CompareCOST;

    @FXML
    private Label CompareCOD;

    @FXML
    private Label CompareBOD;

    @FXML
    private Label CompareTSS;

    @FXML
    private TableView<Print> BestResultView;

    @FXML
    private TableColumn<Print,String> Preliminary;

    @FXML
    private TableColumn<Print,String> Chemical;

    @FXML
    private TableColumn<Print,String> Biological;

    @FXML
    private TableColumn<Print,String> Tertiary;

    @FXML
    private TableColumn<Print,String> Sludge;

    @FXML
    private TableColumn<Print,Double> TSSTable;

    @FXML
    private TableColumn<Print,Double> CODTable;

    @FXML
    private TableColumn<Print,Double> BODTable;

    @FXML
    private TableColumn<Print,Double> COSTTable;


    @FXML
    public void initialize(){
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        Print rowData=DisplayResult.rowData;
        ArrayList<Result>BestResultList;
        double[] COD = rowData.fullCOD;
        double[] BOD = rowData.fullBOD;
        double[] TSS = rowData.fullTSS;
        setStage1(rowData.getTreatmentsA(),String.valueOf(df.format(COD[0])),String.valueOf(df.format(BOD[0])),String.valueOf(df.format(TSS[0])));
        setStage2(rowData.getTreatmentsB(),String.valueOf(df.format(COD[1])),String.valueOf(df.format(BOD[1])),String.valueOf(df.format(TSS[1])));
        setStage3(rowData.getTreatmentsC(),String.valueOf(df.format(COD[2])),String.valueOf(df.format(BOD[2])),String.valueOf(df.format(TSS[2])));
        setStage4(rowData.getTreatmentsD(),String.valueOf(df.format(COD[3])),String.valueOf(df.format(BOD[3])),String.valueOf(df.format(TSS[3])));
        setStage5(rowData.getTreatmentsE(),String.valueOf(df.format(COD[4])),String.valueOf(df.format(BOD[4])),String.valueOf(df.format(TSS[4])));
        setCurrentFinal(String.valueOf(df.format(COD[4])),String.valueOf(df.format(BOD[4])),String.valueOf(df.format(TSS[4])),String.valueOf(df.format(rowData.cost)));
        Preliminary.setCellValueFactory(new PropertyValueFactory<>("treatmentsA"));
        Chemical.setCellValueFactory(new PropertyValueFactory<>("treatmentsB"));
        Biological.setCellValueFactory(new PropertyValueFactory<>("treatmentsC"));
        Tertiary.setCellValueFactory(new PropertyValueFactory<>("treatmentsD"));
        Sludge.setCellValueFactory(new PropertyValueFactory<>("treatmentsE"));
        TSSTable.setCellValueFactory(new PropertyValueFactory<>("TSS"));
        CODTable.setCellValueFactory(new PropertyValueFactory<>("COD"));
        BODTable.setCellValueFactory(new PropertyValueFactory<>("BOD"));
        COSTTable.setCellValueFactory(new PropertyValueFactory<>("cost"));
        menu.UniformSearch(1);
        BestResultView.setItems(menu.getBestTable());
        autoResizeColumns(BestResultView);
        BestResultList=menu.getBestResults();
        for(Result print : BestResultList) {
            setBestFinal(String.valueOf(df.format(print.getFinalCOD())),String.valueOf(df.format(print.getFinalBOD())),String.valueOf(df.format(print.getFinalTSS())),String.valueOf(df.format(print.getFinalCost())));
            setCompareResult(calculation(COD[4],print.getFinalCOD()),calculation(BOD[4],print.getFinalBOD()),calculation(TSS[4],print.getFinalTSS()),calculation(rowData.cost, print.getFinalCost()));
        }


    }

    public void setStage1(String stage1Name,String stage1CoD,String stage1BoD,String stage1Tss) {
        Stage1Name.setText(stage1Name);
        Stage1CoD.setText(stage1CoD);
        Stage1BoD.setText(stage1BoD);
        Stage1Tss.setText(stage1Tss);

    }

    public void setStage2(String stage2Name,String stage2CoD,String stage2BoD,String stage2Tss) {
        Stage2Name.setText(stage2Name);
        Stage2CoD.setText(stage2CoD);
        Stage2Bod.setText(stage2BoD);
        Stage2Tss.setText(stage2Tss);

    }

    public void setStage3(String stage3Name,String stage3CoD,String stage3BoD,String stage3Tss) {
        Stage3Name.setText(stage3Name);
        Stage3CoD.setText(stage3CoD);
        Stage3BoD.setText(stage3BoD);
        Stage3Tss.setText(stage3Tss);

    }

    public void setStage4(String stage4Name,String stage4CoD,String stage4BoD,String stage4Tss) {
        Stage4Name.setText(stage4Name);
        Stage4CoD.setText(stage4CoD);
        Stage4BoD.setText(stage4BoD);
        Stage4Tss.setText(stage4Tss);

    }

    public void setStage5(String stage5Name,String stage5CoD,String stage5BoD,String stage5Tss) {
        Stage5Name.setText(stage5Name);
        Stage5CoD.setText(stage5CoD);
        Stage5BoD.setText(stage5BoD);
        Stage5Tss.setText(stage5Tss);

    }

    public void setCurrentFinal(String COD ,String BOD, String Tss , String Cost){
        CurrentFinalCOD.setText(COD);
        CurrentFinalBOD.setText(BOD);
        CurrentFinalTss.setText(Tss);
        CurrentFinalCOST.setText(Cost);
    }

    public void setBestFinal(String COD ,String BOD, String Tss , String Cost){
        BestFinalCOD.setText(COD);
        BestFinalBOD.setText(BOD);
        BestFinalTss.setText(Tss);
        BestFinalCOST.setText(Cost);
    }

    public void setCompareResult(String COD ,String BOD, String Tss , String Cost){
        CompareCOD.setText(COD);
        CompareBOD.setText(BOD);
        CompareTSS.setText(Tss);
        CompareCOST.setText(Cost);
    }

    public static void autoResizeColumns( TableView<Print> table )
    {
        //Set the right policy
        table.setColumnResizePolicy( TableView.UNCONSTRAINED_RESIZE_POLICY);
        table.getColumns().forEach( (column) ->
        {
            Text t = new Text( column.getText() );
            double max = t.getLayoutBounds().getWidth();
            for ( int i = 0; i < table.getItems().size(); i++ )
            {
                if ( column.getCellData( i ) != null )
                {
                    t = new Text( column.getCellData( i ).toString() );
                    double CalWidth = t.getLayoutBounds().getWidth();
                    if ( CalWidth > max )
                    {
                        max = CalWidth;
                    }
                }
            }
            column.setPrefWidth( max + 10.0d );
        } );
    }
    public String calculation(double current,double best){
        return String.valueOf((int) (best/current*100));
    }
}
