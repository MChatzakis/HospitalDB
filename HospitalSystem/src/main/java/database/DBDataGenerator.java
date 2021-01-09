package database;

import database.entities.users.Coordinator;
import database.entities.users.Doctor;

import database.entities.medics.Drug;
import database.entities.DutyTime;
import database.entities.Examination;
import database.entities.medics.Illness;
import database.entities.Medical;
import database.entities.ReExamination;
import database.entities.Visit;

import database.entities.users.Nurse;
import database.entities.users.Patient;
import database.relations.OnDutyDoctors;
import database.relations.OnDutyNurses;
import database.relations.OnDutyWorkers;

import java.sql.SQLException;

/**
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 */
public class DBDataGenerator {

    public void insertData() throws SQLException, ClassNotFoundException {
        insertDoctors();
        insertNurses();
        insertCoordinators();
        insertIllnesses();
        insertDrugs();
        insertDuties();
        insertPatients();
        insertVisit();
        insertExams();
    }

    public void insertDoctors() throws SQLException, ClassNotFoundException {
        insertCardiologists();
        insertPathologists();
        insertEndocrinologists();
        insertOrthopedics();
        insertPulmonologists();
    }

    public void insertNurses() throws SQLException, ClassNotFoundException {
        Nurse nurse = new Nurse();
        nurse.addNurse("evach", "evach123", "Eva", "Chamilaki", "Hrakleio", "ch@tep.gr", "+30- 69-055-5170", "AT1010");
        nurse.addNurse("effieProb", "effie123", "Effie", "Probona", "Hrakleio", "eff@tep.gr", "null", "KA9955");
        nurse.addNurse("dimiTsichl", "dimiTsichl123", "Dimitra", "Tsichla", "Hrakleio", "tsichla@tep.gr", "null", "IU9976");
        nurse.addNurse("olipopa", "olipopa123", "Olimpia", "Popa", "Hrakleio", "popa@tep.gr", "null", "GH4213");
        nurse.addNurse("nikimark", "nikimark123", "Niki", "Markatou", "Hrakleio", "nikimark@tep.gr", "null", "BC8976");
        nurse.addNurse("mariass", "maris123", "Maria", "Papadak", "Hrakleio", "marpapadaki@tep.gr", "null", "IO9921");
    }

    public void insertCoordinators() throws SQLException, ClassNotFoundException {
        Coordinator coord = new Coordinator();
        coord.addCoordinator("alvin", "alvin123", "Alvi", "Nikola", "Hrakleio", "alvi@tep.gr", "null", "AN6768");
        coord.addCoordinator("billp", "billp123", "Vasilis", "Plevris", "Hrakleio", "billplevr@tep.gr", "null", "SD5524");
        coord.addCoordinator("katePetr", "katerinapetr123", "Katerina", "Petraki", "Hrakleio", "petrKate@tep.gr", "null", "AS7765");
        coord.addCoordinator("matDamon", "matt123", "Mathaios", "Iliakis", "Hrakleio", "mattdamon@tep.gr", "null", "XD4323");
        coord.addCoordinator("thanosStamp", "thanos123", "Thanos", "Staboulos", "Hrakleio", "stabthanos@tep.gr", "null", "VB6243");
    }

    public void insertDrugs() throws SQLException, ClassNotFoundException {
        Drug drug = new Drug();
        drug.addDrug("Dexamethasone", "Pill", "90mg", "1");
        drug.addDrug("Flecainide ", "Pill", "50mg", "2");
        drug.addDrug("Tamiflu ", "Pill", "80mg", "3");
        drug.addDrug("Splint", "Applicable", "NULL", "4");
        drug.addDrug("Amlodipine ", "Pill", "10mg", "5");
    }

    public void insertIllnesses() throws SQLException, ClassNotFoundException {
        Illness ill = new Illness();
        ill.addIllness("Covid-19");
        /*Pneumologos*/
        ill.addIllness("Arrhythmia");
        /*Cardiologos*/
        ill.addIllness("Influenza");
        /*Pathologos*/
        ill.addIllness("Bone_Break");
        /*Orthopedikos*/
        ill.addIllness("Hypertension");
        /*Endokrinologos*/
    }

    public void insertDuties() throws SQLException, ClassNotFoundException {
        DutyTime duty = new DutyTime();
        OnDutyDoctors duty_doc = new OnDutyDoctors();
        OnDutyNurses duty_nur = new OnDutyNurses();
        OnDutyWorkers duty_wor = new OnDutyWorkers();

        duty.addDutyTime("2021-01-06", "24");
        duty.addDutyTime("2021-01-07", "25");
        duty.addDutyTime("2021-01-08", "26");
        duty.addDutyTime("2021-01-09", "27");
        duty.addDutyTime("2021-01-10", "27");

        /*doctors*/
        duty_doc.addDoctorDutyTime("1", "1");
        duty_doc.addDoctorDutyTime("6", "1");
        duty_doc.addDoctorDutyTime("9", "1");
        duty_doc.addDoctorDutyTime("12", "1");
        duty_doc.addDoctorDutyTime("15", "1");

        duty_doc.addDoctorDutyTime("2", "2");
        duty_doc.addDoctorDutyTime("7", "2");
        duty_doc.addDoctorDutyTime("10", "2");
        duty_doc.addDoctorDutyTime("13", "2");
        duty_doc.addDoctorDutyTime("16", "2");

        duty_doc.addDoctorDutyTime("3", "3");
        duty_doc.addDoctorDutyTime("8", "3");
        duty_doc.addDoctorDutyTime("11", "3");
        duty_doc.addDoctorDutyTime("14", "3");
        duty_doc.addDoctorDutyTime("17", "3");

        duty_doc.addDoctorDutyTime("1", "4");
        duty_doc.addDoctorDutyTime("6", "4");
        duty_doc.addDoctorDutyTime("9", "4");
        duty_doc.addDoctorDutyTime("12", "4");
        duty_doc.addDoctorDutyTime("15", "4");

        duty_doc.addDoctorDutyTime("2", "5");
        duty_doc.addDoctorDutyTime("7", "5");
        duty_doc.addDoctorDutyTime("10", "5");
        duty_doc.addDoctorDutyTime("13", "5");
        duty_doc.addDoctorDutyTime("16", "5");

        /*nurses*/
        duty_nur.addNurseDutyTime("18", "1");
        duty_nur.addNurseDutyTime("19", "1");
        duty_nur.addNurseDutyTime("20", "1");

        duty_nur.addNurseDutyTime("21", "2");
        duty_nur.addNurseDutyTime("22", "2");
        duty_nur.addNurseDutyTime("23", "2");

        duty_nur.addNurseDutyTime("18", "3");
        duty_nur.addNurseDutyTime("19", "3");
        duty_nur.addNurseDutyTime("20", "3");

        duty_nur.addNurseDutyTime("21", "4");
        duty_nur.addNurseDutyTime("22", "4");
        duty_nur.addNurseDutyTime("23", "4");

        duty_nur.addNurseDutyTime("18", "5");
        duty_nur.addNurseDutyTime("19", "5");
        duty_nur.addNurseDutyTime("20", "5");

        /*workers*/
        duty_wor.addWorkerDutyTime("24", "1");
        duty_wor.addWorkerDutyTime("25", "1");

        duty_wor.addWorkerDutyTime("26", "2");
        duty_wor.addWorkerDutyTime("27", "2");

        duty_wor.addWorkerDutyTime("26", "3");
        duty_wor.addWorkerDutyTime("27", "3");

        duty_wor.addWorkerDutyTime("28", "4");
        duty_wor.addWorkerDutyTime("24", "4");

        duty_wor.addWorkerDutyTime("25", "5");
        duty_wor.addWorkerDutyTime("26", "5");

    }

    public void insertCardiologists() throws SQLException, ClassNotFoundException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("chatz", "manos123", "Manos", "Chatzakis", "Hrakleio", "chatzakis@tep.gr", "null", "cardiologist", "AT5555");
        doctor.addDoctor("gchatz", "gio123", "George", "Chatzakis", "Hrakleio", "chatzakis_george@tep.gr", "null", "cardiologist", "AT1019");
        doctor.addDoctor("kourl", "kourl123", "Marilena", "Kourletaki", "Hrakleio", "kourl@tep.gr", "null", "cardiologist", "AT2029");
        doctor.addDoctor("katechatz", "kate123", "Katerina", "Chatzaki", "Hrakleio", "kate_ch@tep.gr", "null", "cardiologist", "UT7465");
        doctor.addDoctor("vasilBorb", "borbant3322", "Vasilis", "Borbantonakis", "Hrakleio", "billBorb@tep.gr", "null", "cardiologist", "UT5612");
    }

    public void insertOrthopedics() throws SQLException, ClassNotFoundException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("georgegk", "george123", "George", "Kokolakis", "Hrakleio", "kokol@tep.gr", "null", "orthopedic", "KL3409");
        doctor.addDoctor("nikoltab", "nikolas321", "Nikolas", "Taboukos", "Hrakleio", "tabouk@tep.gr", "null", "orthopedic", "PI9987");
        doctor.addDoctor("leutSpinth", "leut123", "Leuteris", "Spinthakis", "Hrakleio", "efraim@tep.gr", "null", "orthopedic", "K75367");
    }

    public void insertPathologists() throws SQLException, ClassNotFoundException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("drosos", "drosos123", "Drosos", "Drosakis", "Hrakleio", "drosos@tep.gr", "null", "pathologist", "AE2359");
        doctor.addDoctor("houst", "hustu123", "Giorgos", "Houstoulakis", "Hrakleio", "just@tep.gr", "null", "pathologist", "FL9012");
        doctor.addDoctor("dimitra", "dimitra123", "Dimitra", "Hristodoulou", "Hrakleio", "dimi@tep.gr", "null", "pathologist", "GH7162");
    }

    public void insertEndocrinologists() throws SQLException, ClassNotFoundException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("elvis", "elvis123", "Elvira", "Sakoudi", "Hrakleio", "sak@tep.gr", "null", "endocrinologist", "MN8866");
        doctor.addDoctor("nikosFan", "nikfan123", "Nikos", "Fanourakis", "Hrakleio", "fanou@tep.gr", "null", "endocrinologist", "PT9911");
        doctor.addDoctor("konto16", "konto@11", "Nikos", "Kontonasios", "Hrakleio", "konto@tep.gr", "null", "endocrinologist", "MN6543");
    }

    public void insertPulmonologists() throws SQLException, ClassNotFoundException {
        Doctor doctor = new Doctor();
        doctor.addDoctor("gfou", "gfou123", "Giorgos", "Fountakis", "Hrakleio", "fount@tep.gr", "null", "pulmonologist", "UT4629");
        doctor.addDoctor("vasilias", "vasilik33", "Nikos", "Vasilikopoulos", "Hrakleio", "vasilik@tep.gr", "null", "pulmonologist", "GF9078");
        doctor.addDoctor("gmallis", "mall123", "Giorgos", "Mallis", "Hrakleio", "mallis@tep.gr", "null", "pulmonologist", "TR5423");
    }

    public void insertPatients() throws SQLException, ClassNotFoundException {
        Patient pat = new Patient();
        pat.addPatient("nickLen", "len123", "Nikos", "Lenakis", "Hrakleio", "len@gmail.com", "null", "1990-11-16", "12066455", "IK8986", "IKA");
        pat.addChronicDisease("29", "Asthma");
        pat.addChronicDisease("29", "Aids");

        pat.addPatient("gPer", "gper123", "Giorgos", "Perakis", "Hrakleio", "per@gmail.com", "null", "1980-09-12", "97046412", "KH6754", "IKA");
        pat.addChronicDisease("30", "Osteoporosis");
        pat.addChronicDisease("30", "Diabetes");

        pat.addPatient("gIwan", "iwannou21", "Giannis", "Iwannou", "Hrakleio", "iwannou@gmail.com", "null", "1970-03-12", "31155415", "UT1213", "IKA");
        pat.addChronicDisease("31", "Arthritis");
        pat.addChronicDisease("31", "Diabetes");

        pat.addPatient("pPetr", "pav123", "Pavlos", "Petrakis", "Hrakleio", "petrakis_pav@gmail.com", "null", "1995-03-02", "80048812", "BG7813", "IKA");
        pat.addChronicDisease("32", "Obesity");
        

        pat.addPatient("marianth", "marianth1234", "Marianthi", "Panagiotaki", "Hrakleio", "marpanagiot@gmail.com", "null", "1962-12-05", "11641112", "TY4312", "IKA");
        pat.addChronicDisease("33", "Cystic_Fibrosis");
        pat.addChronicDisease("33", "Heart_Disease");
        pat.addChronicDisease("33", "Obesity");

    }

    public void insertVisit() throws SQLException, ClassNotFoundException {
        Visit vis = new Visit();
        
        vis.addVisit("2021-01-06", "1", "29");
        vis.addSymptom("1", "Fever");
        vis.addSymptom("1", "Cough");
        vis.addSymptom("1", "Sore_Thoat");

        vis.addVisit("2021-01-06", "1", "30");
        vis.addSymptom("2", "Arm_Pain");

        vis.addVisit("2021-01-06", "1", "31");
        vis.addSymptom("3", "Taste_Lack");
        vis.addSymptom("3", "Headache");

        vis.addVisit("2021-01-07", "2", "29");
        vis.addSymptom("4", "Heartbeats");

    }

    public void insertExams() throws SQLException, ClassNotFoundException {
        Examination ex = new Examination();
        Medical med = new Medical();
        ReExamination reEx = new ReExamination();

        ex.addExamination("29", "1", "1", "1", "1", "2021-01-06");
        med.addMedical("Covid-19", "1", "29", "1", "20", "2021-01-06");
        reEx.addReExamination("29", "1", "1", "2021-01-01", "1", true);

        ex.addExamination("30", "12", "4", "4", "2", "2021-01-06");
        med.addMedical("xRay", "2", "30", "12", "19", "2021-01-06");
        reEx.addReExamination("30", "12", "2", "2021-01-06", "2", false);

        ex.addExamination("31", "15", "null", "null", "3", "2021-01-06");
        med.addMedical("Covid-19", "3", "31", "15", "18", "2021-01-06");
        reEx.addReExamination("31", "15", "3", "2021-01-06", "3", true);

        ex.addExamination("29", "16", "1", "1", "4", "2021-01-07");
        med.addMedical("Covid-19", "4", "29", "16", "21", "2021-01-07");
        reEx.addReExamination("29", "16", "4", "2021-01-07", "4", true);
    }
}
