package Twed;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Zil on 2016/9/23.
 */

@XmlRootElement( name = "RiverStageObservatoryProfile" )
public class RiverStageObservatoryProfile {
    public String AffiliatedBasin;
    public String AffiliatedSubsidiaryBasin;
    public String AffiliatedSubSubsidiaryBasin;
    public String AlertLevel1;
    public String AlertLevel2;
    public String AlertLevel3;
    public String BasinIdentifier;
    public String BrittlenessStatus;
    public String CarDriveDistanceInHours;
    public String CityElectricitySupplyStatus;
    public String ConstructionManagement;
    public String CrossRiverStructuresNameOfEquipment;
    public String DataCollectionFrequecy;
    public String DataSubmitted;
    public String DrainageStatus;
    public String EcologicalEnvironmentMonitoring;
    public String ElevationOfWaterLevelZeroPoint;
    public String EnglishAddress;
    public String EnglishName;
    public String EnglishRiverName;
    public String EquipmentStatus;
    public String EquipmentStatusOnCrossRiverStructures;
    public String FloodPreventionPurpose;
    public String HighSedimentStatus;
    public String HydroFacilityManagement;
    public String HydrologicalMonitoringPurpose;
    public String LightningStatus;
    public String LocationAddress;
    public LocationByTWD67 LocationByTWD67;
    public LocationByTWD97 LocationByTWD97;
    public String MaintainCycle;
    public String NoPeriodicalDataSubmissionReason;
    public String NormalObservationType;
    public String ObervationItems;
    public String ObservationReason;
    public String ObservationStatus;
    public String ObservatoryIdentifier;
    public String ObservatoryName;
    public String ObservatoryReplacementStatus;
    public String OnSiteDataCollection;
    public String OtherRequirement;
    public String RealTimeDataDeliveryFrequency;
    public String RealTimeDataDeliveryFrequencyInFloodDefenceTime;
    public String Remarks;
    public String RiverName;
    public String RiverSectionDepositionAndErosionChange;
    public String SetDate;
    public String ShortObservationType;
    public String SolarPotentialDemage;
    public String SolarStatus;
    public String SolarWatt;
    public String StealStatus;
    public String StraightRiverStatus;
    public String SubsidenceStatus;
    public String SunLightCoverageStatus;
    public String SunshineStatus;
    public String TideStatus;
    public String Town;
    public String TownIdentifier;
    public String TransmissionEquipment;
    public String VerticalDatumSource;
    public String WalkDistanceInHours;
    public String WaterDrawStatus;
    public String WaterResourceDistrictIdentifier;
    public String WirelessTransmissionType;

    public RiverStageObservatoryProfile() {
    }

    @XmlElement( name = "AffiliatedBasin" )
    public String getAffiliatedBasin() {
        return AffiliatedBasin;
    }

    public void setAffiliatedBasin(String affiliatedBasin) {
        AffiliatedBasin = affiliatedBasin;
    }

    @XmlElement( name = "AffiliatedSubsidiaryBasin" )
    public String getAffiliatedSubsidiaryBasin() {
        return AffiliatedSubsidiaryBasin;
    }

    public void setAffiliatedSubsidiaryBasin(String affiliatedSubsidiaryBasin) {
        AffiliatedSubsidiaryBasin = affiliatedSubsidiaryBasin;
    }

    @XmlElement( name = "AffiliatedSubSubsidiaryBasin" )
    public String getAffiliatedSubSubsidiaryBasin() {
        return AffiliatedSubSubsidiaryBasin;
    }

    public void setAffiliatedSubSubsidiaryBasin(String affiliatedSubSubsidiaryBasin) {
        AffiliatedSubSubsidiaryBasin = affiliatedSubSubsidiaryBasin;
    }

    @XmlElement( name = "AlertLevel1" )
    public String getAlertLevel1() {
        return AlertLevel1;
    }

    public void setAlertLevel1(String alertLevel1) {
        AlertLevel1 = alertLevel1;
    }

    @XmlElement( name = "AlertLevel2" )
    public String getAlertLevel2() {
        return AlertLevel2;
    }

    public void setAlertLevel2(String alertLevel2) {
        AlertLevel2 = alertLevel2;
    }

    @XmlElement( name = "AlertLevel3" )
    public String getAlertLevel3() {
        return AlertLevel3;
    }

    public void setAlertLevel3(String alertLevel3) {
        AlertLevel3 = alertLevel3;
    }

    @XmlElement( name = "BasinIdentifier" )
    public String getBasinIdentifier() {
        return BasinIdentifier;
    }

    public void setBasinIdentifier(String basinIdentifier) {
        BasinIdentifier = basinIdentifier;
    }

    @XmlElement( name = "BrittlenessStatus" )
    public String getBrittlenessStatus() {
        return BrittlenessStatus;
    }

    public void setBrittlenessStatus(String brittlenessStatus) {
        BrittlenessStatus = brittlenessStatus;
    }

    @XmlElement( name = "CarDriveDistanceInHours" )
    public String getCarDriveDistanceInHours() {
        return CarDriveDistanceInHours;
    }

    public void setCarDriveDistanceInHours(String carDriveDistanceInHours) {
        CarDriveDistanceInHours = carDriveDistanceInHours;
    }

    @XmlElement( name = "CityElectricitySupplyStatus" )
    public String getCityElectricitySupplyStatus() {
        return CityElectricitySupplyStatus;
    }

    public void setCityElectricitySupplyStatus(String cityElectricitySupplyStatus) {
        CityElectricitySupplyStatus = cityElectricitySupplyStatus;
    }

    @XmlElement( name = "ConstructionManagement" )
    public String getConstructionManagement() {
        return ConstructionManagement;
    }

    public void setConstructionManagement(String constructionManagement) {
        ConstructionManagement = constructionManagement;
    }

    @XmlElement( name = "CrossRiverStructuresNameOfEquipment" )
    public String getCrossRiverStructuresNameOfEquipment() {
        return CrossRiverStructuresNameOfEquipment;
    }

    public void setCrossRiverStructuresNameOfEquipment(String crossRiverStructuresNameOfEquipment) {
        CrossRiverStructuresNameOfEquipment = crossRiverStructuresNameOfEquipment;
    }

    @XmlElement( name = "DataCollectionFrequecy" )
    public String getDataCollectionFrequecy() {
        return DataCollectionFrequecy;
    }

    public void setDataCollectionFrequecy(String dataCollectionFrequecy) {
        DataCollectionFrequecy = dataCollectionFrequecy;
    }

    @XmlElement( name = "DataSubmitted" )
    public String getDataSubmitted() {
        return DataSubmitted;
    }

    public void setDataSubmitted(String dataSubmitted) {
        DataSubmitted = dataSubmitted;
    }

    @XmlElement( name = "DrainageStatus" )
    public String getDrainageStatus() {
        return DrainageStatus;
    }

    public void setDrainageStatus(String drainageStatus) {
        DrainageStatus = drainageStatus;
    }

    @XmlElement( name = "EcologicalEnvironmentMonitoring" )
    public String getEcologicalEnvironmentMonitoring() {
        return EcologicalEnvironmentMonitoring;
    }

    public void setEcologicalEnvironmentMonitoring(String ecologicalEnvironmentMonitoring) {
        EcologicalEnvironmentMonitoring = ecologicalEnvironmentMonitoring;
    }

    @XmlElement( name = "ElevationOfWaterLevelZeroPoint" )
    public String getElevationOfWaterLevelZeroPoint() {
        return ElevationOfWaterLevelZeroPoint;
    }

    public void setElevationOfWaterLevelZeroPoint(String elevationOfWaterLevelZeroPoint) {
        ElevationOfWaterLevelZeroPoint = elevationOfWaterLevelZeroPoint;
    }

    @XmlElement( name = "EnglishAddress" )
    public String getEnglishAddress() {
        return EnglishAddress;
    }

    public void setEnglishAddress(String englishAddress) {
        EnglishAddress = englishAddress;
    }

    @XmlElement( name = "EnglishName" )
    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String englishName) {
        EnglishName = englishName;
    }

    @XmlElement( name = "EnglishRiverName" )
    public String getEnglishRiverName() {
        return EnglishRiverName;
    }

    public void setEnglishRiverName(String englishRiverName) {
        EnglishRiverName = englishRiverName;
    }

    @XmlElement( name = "EquipmentStatus" )
    public String getEquipmentStatus() {
        return EquipmentStatus;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        EquipmentStatus = equipmentStatus;
    }

    @XmlElement( name = "EquipmentStatusOnCrossRiverStructures" )
    public String getEquipmentStatusOnCrossRiverStructures() {
        return EquipmentStatusOnCrossRiverStructures;
    }

    public void setEquipmentStatusOnCrossRiverStructures(String equipmentStatusOnCrossRiverStructures) {
        EquipmentStatusOnCrossRiverStructures = equipmentStatusOnCrossRiverStructures;
    }

    @XmlElement( name = "FloodPreventionPurpose" )
    public String getFloodPreventionPurpose() {
        return FloodPreventionPurpose;
    }

    public void setFloodPreventionPurpose(String floodPreventionPurpose) {
        FloodPreventionPurpose = floodPreventionPurpose;
    }

    @XmlElement( name = "HighSedimentStatus" )
    public String getHighSedimentStatus() {
        return HighSedimentStatus;
    }

    public void setHighSedimentStatus(String highSedimentStatus) {
        HighSedimentStatus = highSedimentStatus;
    }

    @XmlElement( name = "HydroFacilityManagement" )
    public String getHydroFacilityManagement() {
        return HydroFacilityManagement;
    }

    public void setHydroFacilityManagement(String hydroFacilityManagement) {
        HydroFacilityManagement = hydroFacilityManagement;
    }

    @XmlElement( name = "HydrologicalMonitoringPurpose" )
    public String getHydrologicalMonitoringPurpose() {
        return HydrologicalMonitoringPurpose;
    }

    public void setHydrologicalMonitoringPurpose(String hydrologicalMonitoringPurpose) {
        HydrologicalMonitoringPurpose = hydrologicalMonitoringPurpose;
    }

    @XmlElement( name = "LightningStatus" )
    public String getLightningStatus() {
        return LightningStatus;
    }

    public void setLightningStatus(String lightningStatus) {
        LightningStatus = lightningStatus;
    }

    @XmlElement( name = "LocationAddress" )
    public String getLocationAddress() {
        return LocationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        LocationAddress = locationAddress;
    }

    @XmlElement( name = "LocationByTWD67" )
    public LocationByTWD67 getLocationByTWD67() {
        return LocationByTWD67;
    }

    public void setLocationByTWD67(LocationByTWD67 locationByTWD67) {
        LocationByTWD67 = locationByTWD67;
    }

    @XmlElement( name = "LocationByTWD97" )
    public LocationByTWD97 getLocationByTWD97() {
        return LocationByTWD97;
    }

    public void setLocationByTWD97(LocationByTWD97 locationByTWD97) {
        LocationByTWD97 = locationByTWD97;
    }

    @XmlElement( name = "MaintainCycle" )
    public String getMaintainCycle() {
        return MaintainCycle;
    }

    public void setMaintainCycle(String maintainCycle) {
        MaintainCycle = maintainCycle;
    }

    @XmlElement( name = "NoPeriodicalDataSubmissionReason" )
    public String getNoPeriodicalDataSubmissionReason() {
        return NoPeriodicalDataSubmissionReason;
    }

    public void setNoPeriodicalDataSubmissionReason(String noPeriodicalDataSubmissionReason) {
        NoPeriodicalDataSubmissionReason = noPeriodicalDataSubmissionReason;
    }

    @XmlElement( name = "NormalObservationType" )
    public String getNormalObservationType() {
        return NormalObservationType;
    }

    public void setNormalObservationType(String normalObservationType) {
        NormalObservationType = normalObservationType;
    }

    @XmlElement( name = "ObervationItems" )
    public String getObervationItems() {
        return ObervationItems;
    }

    public void setObervationItems(String obervationItems) {
        ObervationItems = obervationItems;
    }

    @XmlElement( name = "ObservationReason" )
    public String getObservationReason() {
        return ObservationReason;
    }

    public void setObservationReason(String observationReason) {
        ObservationReason = observationReason;
    }

    @XmlElement( name = "ObservationStatus" )
    public String getObservationStatus() {
        return ObservationStatus;
    }

    public void setObservationStatus(String observationStatus) {
        ObservationStatus = observationStatus;
    }

    @XmlElement( name = "ObservatoryIdentifier" )
    public String getObservatoryIdentifier() {
        return ObservatoryIdentifier;
    }

    public void setObservatoryIdentifier(String observatoryIdentifier) {
        ObservatoryIdentifier = observatoryIdentifier;
    }

    @XmlElement( name = "ObservatoryName" )
    public String getObservatoryName() {
        return ObservatoryName;
    }

    public void setObservatoryName(String observatoryName) {
        ObservatoryName = observatoryName;
    }

    @XmlElement( name = "ObservatoryReplacementStatus" )
    public String getObservatoryReplacementStatus() {
        return ObservatoryReplacementStatus;
    }

    public void setObservatoryReplacementStatus(String observatoryReplacementStatus) {
        ObservatoryReplacementStatus = observatoryReplacementStatus;
    }

    @XmlElement( name = "OnSiteDataCollection" )
    public String getOnSiteDataCollection() {
        return OnSiteDataCollection;
    }

    public void setOnSiteDataCollection(String onSiteDataCollection) {
        OnSiteDataCollection = onSiteDataCollection;
    }

    @XmlElement( name = "OtherRequirement" )
    public String getOtherRequirement() {
        return OtherRequirement;
    }

    public void setOtherRequirement(String otherRequirement) {
        OtherRequirement = otherRequirement;
    }

    @XmlElement( name = "RealTimeDataDeliveryFrequency" )
    public String getRealTimeDataDeliveryFrequency() {
        return RealTimeDataDeliveryFrequency;
    }

    public void setRealTimeDataDeliveryFrequency(String realTimeDataDeliveryFrequency) {
        RealTimeDataDeliveryFrequency = realTimeDataDeliveryFrequency;
    }

    @XmlElement( name = "RealTimeDataDeliveryFrequencyInFloodDefenceTime" )
    public String getRealTimeDataDeliveryFrequencyInFloodDefenceTime() {
        return RealTimeDataDeliveryFrequencyInFloodDefenceTime;
    }

    public void setRealTimeDataDeliveryFrequencyInFloodDefenceTime(String realTimeDataDeliveryFrequencyInFloodDefenceTime) {
        RealTimeDataDeliveryFrequencyInFloodDefenceTime = realTimeDataDeliveryFrequencyInFloodDefenceTime;
    }

    @XmlElement( name = "Remarks" )
    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    @XmlElement( name = "RiverName" )
    public String getRiverName() {
        return RiverName;
    }

    public void setRiverName(String riverName) {
        RiverName = riverName;
    }

    @XmlElement( name = "RiverSectionDepositionAndErosionChange" )
    public String getRiverSectionDepositionAndErosionChange() {
        return RiverSectionDepositionAndErosionChange;
    }

    public void setRiverSectionDepositionAndErosionChange(String riverSectionDepositionAndErosionChange) {
        RiverSectionDepositionAndErosionChange = riverSectionDepositionAndErosionChange;
    }

    @XmlElement( name = "SetDate" )
    public String getSetDate() {
        return SetDate;
    }

    public void setSetDate(String setDate) {
        SetDate = setDate;
    }

    @XmlElement( name = "ShortObservationType" )
    public String getShortObservationType() {
        return ShortObservationType;
    }

    public void setShortObservationType(String shortObservationType) {
        ShortObservationType = shortObservationType;
    }

    @XmlElement( name = "SolarPotentialDemage" )
    public String getSolarPotentialDemage() {
        return SolarPotentialDemage;
    }

    public void setSolarPotentialDemage(String solarPotentialDemage) {
        SolarPotentialDemage = solarPotentialDemage;
    }

    @XmlElement( name = "SolarStatus" )
    public String getSolarStatus() {
        return SolarStatus;
    }

    public void setSolarStatus(String solarStatus) {
        SolarStatus = solarStatus;
    }

    @XmlElement( name = "SolarWatt" )
    public String getSolarWatt() {
        return SolarWatt;
    }

    public void setSolarWatt(String solarWatt) {
        SolarWatt = solarWatt;
    }

    @XmlElement( name = "StealStatus" )
    public String getStealStatus() {
        return StealStatus;
    }

    public void setStealStatus(String stealStatus) {
        StealStatus = stealStatus;
    }

    @XmlElement( name = "StraightRiverStatus" )
    public String getStraightRiverStatus() {
        return StraightRiverStatus;
    }

    public void setStraightRiverStatus(String straightRiverStatus) {
        StraightRiverStatus = straightRiverStatus;
    }

    @XmlElement( name = "SubsidenceStatus" )
    public String getSubsidenceStatus() {
        return SubsidenceStatus;
    }

    public void setSubsidenceStatus(String subsidenceStatus) {
        SubsidenceStatus = subsidenceStatus;
    }

    @XmlElement( name = "SunLightCoverageStatus" )
    public String getSunLightCoverageStatus() {
        return SunLightCoverageStatus;
    }

    public void setSunLightCoverageStatus(String sunLightCoverageStatus) {
        SunLightCoverageStatus = sunLightCoverageStatus;
    }

    @XmlElement( name = "SunshineStatus" )
    public String getSunshineStatus() {
        return SunshineStatus;
    }

    public void setSunshineStatus(String sunshineStatus) {
        SunshineStatus = sunshineStatus;
    }

    @XmlElement( name = "TideStatus" )
    public String getTideStatus() {
        return TideStatus;
    }

    public void setTideStatus(String tideStatus) {
        TideStatus = tideStatus;
    }

    @XmlElement( name = "Town" )
    public String getTown() {
        return Town;
    }

    public void setTown(String town) {
        Town = town;
    }

    @XmlElement( name = "TownIdentifier" )
    public String getTownIdentifier() {
        return TownIdentifier;
    }

    public void setTownIdentifier(String townIdentifier) {
        TownIdentifier = townIdentifier;
    }

    @XmlElement( name = "TransmissionEquipment" )
    public String getTransmissionEquipment() {
        return TransmissionEquipment;
    }

    public void setTransmissionEquipment(String transmissionEquipment) {
        TransmissionEquipment = transmissionEquipment;
    }

    @XmlElement( name = "VerticalDatumSource" )
    public String getVerticalDatumSource() {
        return VerticalDatumSource;
    }

    public void setVerticalDatumSource(String verticalDatumSource) {
        VerticalDatumSource = verticalDatumSource;
    }

    @XmlElement( name = "WalkDistanceInHours" )
    public String getWalkDistanceInHours() {
        return WalkDistanceInHours;
    }

    public void setWalkDistanceInHours(String walkDistanceInHours) {
        WalkDistanceInHours = walkDistanceInHours;
    }

    @XmlElement( name = "WaterDrawStatus" )
    public String getWaterDrawStatus() {
        return WaterDrawStatus;
    }

    public void setWaterDrawStatus(String waterDrawStatus) {
        WaterDrawStatus = waterDrawStatus;
    }

    @XmlElement( name = "WaterResourceDistrictIdentifier" )
    public String getWaterResourceDistrictIdentifier() {
        return WaterResourceDistrictIdentifier;
    }

    public void setWaterResourceDistrictIdentifier(String waterResourceDistrictIdentifier) {
        WaterResourceDistrictIdentifier = waterResourceDistrictIdentifier;
    }

    @XmlElement( name = "WirelessTransmissionType" )
    public String getWirelessTransmissionType() {
        return WirelessTransmissionType;
    }

    public void setWirelessTransmissionType(String wirelessTransmissionType) {
        WirelessTransmissionType = wirelessTransmissionType;
    }
}

