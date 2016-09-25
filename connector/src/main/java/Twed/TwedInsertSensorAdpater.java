package Twed;

import helper.InsertSensorML20Helper;
import insertSensorML20.*;

import java.util.LinkedList;

/**
 * Created by Zil on 2016/9/24.
 */
public class TwedInsertSensorAdpater extends Object {

    private TaiwanWaterExchangingData twed;
    private RiverStageObservatoryProfile profile;
    private InsertSensor insertSensor = new InsertSensor();
    private InsertSensorML20Helper helper = new InsertSensorML20Helper();

    public TwedInsertSensorAdpater(TaiwanWaterExchangingData twed){
        this.twed = twed;
        profile = twed.getHydrologyRiverClass().getRiverStageObservatoryProfile();
    }

    public InsertSensor getInsertSensor(){
        setIdentifier();
        setIdentification();
        setCharacteristics();
        setCapabilities();
        setDocumentation();
        setDocumentation();
        setInput();
        setOutput();
        setObservableProperties();
        setObservationTypes();
        return insertSensor;
    }

    private void setIdentifier(){
        Identifier identifier = new Identifier();
        identifier.setPrefix("urn:ogc:object:feature:Sensor:twed");
        identifier.setName(profile.getBasinIdentifier());
        insertSensor.setIdentifier(identifier);
    }

    private void setIdentification(){
        Identification identification = new Identification();
        Identifier name1 = new Identifier();
        Identifier name2 = new Identifier();
        Identifier name3 = new Identifier();
        identification.setIdentifierList(new Identifier[]{ name1, name2, name3 });
        if( profile.getObservatoryName() != null )
            name1.setTerm( new Term("http://sensorml.com/ont/swe/property/ChineseName", "Chinese Name", profile.getObservatoryName()));
        if( profile.getEnglishName() != null )
            name2.setTerm( new Term("http://sensorml.com/ont/swe/property/EnglishName", "English Name", profile.getEnglishName()) );
        if( profile.getBasinIdentifier() != null )
            name3.setTerm( new Term("http://sensorml.com/ont/swe/property/ChineseFullName", "Chinese Full Name", profile.getBasinIdentifier())  );
        insertSensor.setIdentification(identification);
    }

    private void setCharacteristics(){
        Characteristic[] characteristics = new Characteristic[6];
        characteristics[0] = new Characteristic();
        characteristics[0].setName("Information");
        characteristics[0].setDataRecord(new DataRecord());
        characteristics[0].getDataRecord().setLabel("測站相關資訊");
        LinkedList<Field> fields = new LinkedList<Field>();
        fields.add(helper.getTextField("Town", "鄉鎮名稱", profile.getTown()));
        fields.add(helper.getTextField("TownIdentifier", "鄉鎮代碼", profile.getTownIdentifier()));
        fields.add(helper.getTextField("AffiliatedBasin", "測站所屬流域(主流)", profile.getAffiliatedBasin()));
        fields.add(helper.getTextField("AffiliatedSubsidiaryBasin", "測站所屬之流域(支流)", profile.getAffiliatedSubsidiaryBasin()));
        fields.add(helper.getTextField("AffiliatedSubSubsidiaryBasin", "測站所屬之流域(次支流)", profile.getAffiliatedSubSubsidiaryBasin()));
        fields.add(helper.getTextField("WaterResourceDistrictIdentifier", "水資源分區編號", profile.getWaterResourceDistrictIdentifier()));
        fields.add(helper.getTextField("CrossRiverStructuresNameOfEquipment", "設置之橋樑或構造物名稱", profile.getCrossRiverStructuresNameOfEquipment()));
        fields.add(helper.getBooleanField("ObservatoryReplacementStatus", profile.getObservatoryReplacementStatus()));
        fields.add(helper.getTextField("NormalObservationType", "正常觀測之性質", profile.getNormalObservationType()));
        fields.add(helper.getTextField("ConstructionManagement", "工程施工管理", profile.getConstructionManagement()));
        fields.add(helper.getTextField("HydroFacilityManagement", "水利設施管理", profile.getHydroFacilityManagement()));
        fields.add(helper.getTextField("HydrologicalMonitoringPurpose", "水文觀測用途", profile.getHydrologicalMonitoringPurpose()));
        fields.add(helper.getTextField("NoPeriodicalDataSubmissionReason", "水文資料無定期送水文組彙整原因", profile.getNoPeriodicalDataSubmissionReason()));
        fields.add(helper.getTextField("EcologicalEnvironmentMonitoring", "生態環境監測", profile.getEcologicalEnvironmentMonitoring()));
        fields.add(helper.getTextField("VerticalDatumSource", "基準高程之引測來源", profile.getVerticalDatumSource()));
        fields.add(helper.getTextField("EquipmentStatus", "設備型態", profile.getEquipmentStatus()));
        fields.add(helper.getTextField("SetDate", "設站日期", profile.getSetDate()));
        fields.add(helper.getTextField("FloodPreventionPurpose", "防汛用途", profile.getFloodPreventionPurpose()));
        fields.add(helper.getTextField("ObervationItems", "觀測項目", profile.getObervationItems()));
        fields.add(helper.getTextField("ObservationStatus", "觀測現況", profile.getObservationStatus()));
        fields.add(helper.getTextField("ObservationReason", "觀測原因", profile.getObservationReason()));
        fields.add(helper.getTextField("OtherRequirement", "特定計畫或特定需求", profile.getOtherRequirement()));
        fields.add(helper.getTextField("ShortObservationType", "短期觀測的原因", profile.getShortObservationType()));
        fields.add(helper.getTextField("WirelessTransmissionType", "無線傳輸方式", profile.getWirelessTransmissionType()));
        fields.add(helper.getTextField("Remarks", "備註", profile.getRemarks()));
        characteristics[0].getDataRecord().setField(fields.toArray(new Field[fields.size()]));

        characteristics[1] = new Characteristic();
        characteristics[1].setName("AlertLevels");
        DataRecord d = new DataRecord();
        d.setLabel("水位警戒高度");
        Field field = new Field();
        field.setName("AlertLevels");
        field.setDataRecord(new DataRecord());
        field.getDataRecord().setField(new Field[3]);
        Field[] fields2 = field.getDataRecord().getField();
        fields2[0] = helper.getQuantityField("Level1", "http://sensorml.com/ont/swe/property/WaterDepth", "m", profile.getAlertLevel1());
        fields2[1] = helper.getQuantityField("Level2", "http://sensorml.com/ont/swe/property/WaterDepth", "m", profile.getAlertLevel2());
        fields2[2] = helper.getQuantityField("Level3", "http://sensorml.com/ont/swe/property/WaterDepth", "m", profile.getAlertLevel3());
        d.setField(new Field[]{field});
        for( Field f: fields2 ){
            if( f != null )
                characteristics[1].setDataRecord(d);
        }

        characteristics[2] = new Characteristic();
        characteristics[2].setName("RiverStatus");
        DataRecord dataRecord = new DataRecord();
        dataRecord.setLabel("河川水位狀態");
        Field field2 = new Field();
        field2.setName("RiverStatus");
        field2.setDataRecord(new DataRecord());
        field2.getDataRecord().setField(new Field[7]);
        Field[] fields3 = field2.getDataRecord().getField();
        fields3[0] = helper.getQuantityField("ElevationOfWaterLevelZeroPoint", "http://sensorml.com/ont/swe/property/WaterDepth", "m", profile.getElevationOfWaterLevelZeroPoint());
        fields3[1] = helper.getBooleanField("BrittlenessStatus", profile.getBrittlenessStatus());
        fields3[2] = helper.getBooleanField("LightningStatus", profile.getLightningStatus());
        fields3[3] = helper.getBooleanField("TransmissionEquipment", profile.getTransmissionEquipment());
        fields3[4] = helper.getBooleanField("SunshineStatus", profile.getSunshineStatus());
        fields3[5] = helper.getBooleanField("TideStatus", profile.getTideStatus());
        fields3[6] = helper.getBooleanField("StealStatus", profile.getStealStatus());
        dataRecord.setField(new Field[]{field2});
        for( Field f: fields3 ) {
            if(f != null)
                characteristics[2].setDataRecord(dataRecord);
        }

        characteristics[3] = new Characteristic();
        characteristics[3].setName("Distance");
        DataRecord dataRecord1 = new DataRecord();
        dataRecord1.setLabel("路程耗時");
        Field field3 = new Field();
        field3.setName("costTime");
        field3.setDataRecord(new DataRecord());
        field3.getDataRecord().setField(new Field[2]);
        Field[] fields4 = field3.getDataRecord().getField();
        fields4[0] = helper.getQuantityField("CarDriveDistanceInHours", "http://sensorml.com/ont/swe/property/hours", "hours", profile.getCarDriveDistanceInHours());
        fields4[1] = helper.getQuantityField("WalkDistanceInHours", "http://sensorml.com/ont/swe/property/hours", "hours", profile.getWalkDistanceInHours());
        dataRecord1.setField(new Field[]{field3});
        for( Field f: fields4 ) {
            if(f != null)
                characteristics[3].setDataRecord(dataRecord1);
        }

        characteristics[4] = new Characteristic();
        characteristics[4].setName("sampling");
        DataRecord dataRecord2 = new DataRecord();
        dataRecord2.setLabel("資料取樣相關");
        Field field4 = new Field();
        field4.setName("CostTime");
        field4.setDataRecord(new DataRecord());
        field4.getDataRecord().setField(new Field[3]);
        Field[] fields5 = field4.getDataRecord().getField();
        fields5[0] = helper.getBooleanField("DataSubmitted", profile.getDataSubmitted());
        fields5[1] = helper.getBooleanField("OnSiteDataCollection", profile.getOnSiteDataCollection());
        fields5[2] = helper.getQuantityField("MaintainCycle", "http://sensorml.com/ont/swe/property/SamplePeriod", "s", profile.getMaintainCycle());
        dataRecord2.setField(new Field[]{field4});
        for( Field f: fields5 ) {
            if(f != null)
                characteristics[4].setDataRecord(dataRecord2);
        }

        characteristics[5] = new Characteristic();
        characteristics[5].setName("electricalRequirements");
        characteristics[5].setDataRecord(new DataRecord());
        characteristics[5].getDataRecord().setLabel("電力相關");
        Field[] fields6 = new Field[4];
        fields6[0] = helper.getBooleanField("DataSubmitted", profile.getDataSubmitted());
        fields6[1] = helper.getBooleanField("SolarStatus", profile.getSolarStatus());
        fields6[2] = helper.getBooleanField("SolarPotentialDemage", profile.getSolarPotentialDemage());
        fields6[3] = helper.getQuantityField("SolarWatt", "http://sensorml.com/ont/swe/property/SolarWatt", "W", profile.getSolarWatt());
        characteristics[5].getDataRecord().setField(fields6);

        insertSensor.setCharacteristics(characteristics);
    }

    private void setCapabilities(){
        Capabilities[] capabilities = new Capabilities[2];
        capabilities[0] = new Capabilities();
        capabilities[0].setName("offerings");
        capabilities[0].setCapability(new Capability[]{new Capability()});
        capabilities[0].getCapability()[0].setName("offeringID");
        Text text = new Text();
        text.setDefinition("urn:ogc:def:identifier:OGC:offeringID");
        text.setLabel("offeringID");
        text.setValue(profile.getBasinIdentifier());
        capabilities[0].getCapability()[0].setText(text);

        capabilities[1] = new Capabilities();
        capabilities[1].setName("sensorCapabilities");
        capabilities[1].setCapability(new Capability[]{ new Capability() });
        capabilities[1].getCapability()[0].setName("measurementProperties");
        DataRecord dataRecord = new DataRecord();
        dataRecord.setDefinition("http://sensorml.com/ont/swe/property/MeasurementProperties");
        dataRecord.setLabel("Measurement Properties");
        dataRecord.setField(new Field[11]);
        Field[] field = dataRecord.getField();
        field[0] = helper.getQuantityField("DataCollectionFrequecy", "http://sensorml.com/ont/swe/property/SamplePeriod", "s", profile.getDataCollectionFrequecy());
        field[1] = helper.getQuantityField("RealTimeDataDeliveryFrequencyInFloodDefenceTime", "http://sensorml.com/ont/swe/property/DataDeliveryFrequency", "s", profile.getRealTimeDataDeliveryFrequencyInFloodDefenceTime());
        field[2] = helper.getQuantityField("RealTimeDataDeliveryFrequency", "http://sensorml.com/ont/swe/property/SamplePeriod", "s", profile.getRealTimeDataDeliveryFrequency());
        field[3] = helper.getBooleanField("SunLightCoverageStatus", profile.getSunLightCoverageStatus());
        field[4] = helper.getBooleanField("EquipmentStatusOnCrossRiverStructures", profile.getEquipmentStatusOnCrossRiverStructures());
        field[5] = helper.getBooleanField("DrainageStatus", profile.getDrainageStatus());
        field[6] = helper.getBooleanField("WaterDrawStatus", profile.getWaterDrawStatus());
        field[7] = helper.getTextField("RiverSectionDepositionAndErosionChange", null, profile.getRiverSectionDepositionAndErosionChange());
        field[8] = helper.getBooleanField("HighSedimentStatus", profile.getHighSedimentStatus());
        field[9] = helper.getBooleanField("StraightRiverStatus", profile.getStraightRiverStatus());
        field[10] = helper.getBooleanField("SubsidenceStatus", profile.getSubsidenceStatus());

        insertSensor.setCapabilities(capabilities);
    }

    private void setDocumentation(){
        Documentation documentation = new Documentation();
        documentation.setUrl("http://twed.wra.gov.tw/System/PureDataDetail.aspx?DicID=55");
        insertSensor.setDocumentation(documentation);
    }

    private void setInput(){
        Input input = new Input();
        input.setName("waterLevel");
        input.setObservableProperty(new ObservableProperty());
        input.getObservableProperty().setDefinition("http://sensorml.com/ont/swe/property/WaterDepth");

        insertSensor.setInput(new Input[]{input});
    }

    private void setOutput(){
        Output[] outputs = {new Output()};
        outputs[0].setName("waterLevel");
        outputs[0].setQuantity(new Quantity());
        outputs[0].getQuantity().setDefinition("http://sensorml.com/ont/swe/property/WaterDepth");
        outputs[0].getQuantity().setLabel("real time water level");
        outputs[0].getQuantity().setUom("m");

        insertSensor.setOutput(outputs);
    }

    private void setObservableProperties(){

        ObservableProperty[] observableProperties = {new ObservableProperty()};
        observableProperties[0].setPrefix("urn:ogc:object:feature:Sensor:twed");
        observableProperties[0].setName("waterLevel");
        insertSensor.setObservableProperties(observableProperties);
    }

    private void setObservationTypes(){
        ObservationType[] observationTypes = {new ObservationType()};
        observationTypes[0].setName("http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement");
        insertSensor.setObservationTypes(observationTypes);
    }
}

