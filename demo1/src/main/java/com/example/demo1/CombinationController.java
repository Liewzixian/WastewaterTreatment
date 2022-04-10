package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
    public void initialize(){
        Print rowData=DisplayResult.rowData;
        double[] COD = rowData.fullCOD;
        double[] BOD = rowData.fullBOD;
        double[] TSS = rowData.fullTSS;
        setStage1(rowData.getTreatmentsA(),String.valueOf(COD[0]),String.valueOf(BOD[0]),String.valueOf(TSS[0]));
        setStage2(rowData.getTreatmentsB(),String.valueOf(COD[1]),String.valueOf(BOD[1]),String.valueOf(TSS[1]));
        setStage3(rowData.getTreatmentsC(),String.valueOf(COD[2]),String.valueOf(BOD[2]),String.valueOf(TSS[2]));
        setStage4(rowData.getTreatmentsD(),String.valueOf(COD[3]),String.valueOf(BOD[3]),String.valueOf(TSS[3]));
        setStage5(rowData.getTreatmentsE(),String.valueOf(COD[4]),String.valueOf(BOD[4]),String.valueOf(TSS[4]));

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
}
