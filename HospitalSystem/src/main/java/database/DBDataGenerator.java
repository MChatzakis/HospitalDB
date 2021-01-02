package database;

import database.entities.users.Coordinator;
import database.entities.users.Doctor;

import database.entities.medics.Drug;
import database.entities.DutyTime;
import database.entities.Examination;
import database.entities.medics.Illness;
import database.entities.Medical;
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
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class DBDataGenerator {

    public void insertData() throws SQLException , ClassNotFoundException{
        insertDoctors();
        insertNurses();
        insertCoordinators();
        insertIllnesses();
        insertDrugs();
        //insertDuties();
        insertPatients();
        //insertVisit();
        //insertInitExams();
        //insertMedicals();
        //insertReExams();
    }

    public void insertDoctors() throws SQLException, ClassNotFoundException {
        insertCardiologists();
        insertPathologists();
        insertEndocrinologists();
        insertGynecologists();
        insertPulmonologists();
    }

    public void insertNurses() throws SQLException , ClassNotFoundException{
        Nurse nurse = new Nurse();
        nurse.addNurse("evach", "evach123", "Eva", "Chamilaki", "Hrakleio", "ch@tep.gr", "null", "AT1010");
        nurse.addNurse("effieProb", "effie123", "Effie", "Probona", "Hrakleio", "eff@tep.gr", "null", "KA9955");
        nurse.addNurse("dimiTsichl", "dimiTsichl123", "Dimitra", "Tsichla", "Hrakleio", "tsichla@tep.gr", "null", "IU9976");
        nurse.addNurse("olipopa", "olipopa123", "Olimpia", "Popa", "Hrakleio", "popa@tep.gr", "null", "GH4213");
        nurse.addNurse("nikimark", "nikimark123", "Niki", "Markatou", "Hrakleio", "nikimark@tep.gr", "null", "BC8976");
    }

    public void insertCoordinators() throws SQLException , ClassNotFoundException{
        Coordinator coord = new Coordinator();
        coord.addCoordinator("alvin", "alvin123", "Alvi", "Nikola", "Hrakleio", "alvi@tep.gr", "null", "AN6768");
        coord.addCoordinator("billp", "billp123", "Vasilis", "Plevris", "Hrakleio", "billplevr@tep.gr", "null", "SD5524");
        coord.addCoordinator("katePetr", "katerinapetr123", "Katerina", "Petraki", "Hrakleio", "petrKate@tep.gr", "null", "AS7765");
        coord.addCoordinator("matDamon", "matt123", "Mathaios", "Iliakis", "Hrakleio", "mattdamon@tep.gr", "null", "XD4323");
        coord.addCoordinator("thanosStamp", "thanos123", "Thanos", "Staboulos", "Hrakleio", "stabthanos@tep.gr", "null", "VB6243");
    }

    public void insertDrugs() throws SQLException , ClassNotFoundException{
        Drug drug = new Drug();
        drug.addDrug("Vaccine", "Vaccine", "500mg", "1");
        drug.addDrug("Aspirin", "Pill", "500mg", "2");
        drug.addDrug("Bandage", "Applicable", "null", "3");
        drug.addDrug("AllergyCream", "Cream", "100mg", "4");
    }

    public void insertIllnesses() throws SQLException , ClassNotFoundException{
        Illness ill = new Illness();
        ill.addIllness("Covid");
        ill.addIllness("Flu");
        ill.addIllness("Bone_Break");
        ill.addIllness("Allergy");
    }

    public void insertDuties() throws SQLException, ClassNotFoundException {
        DutyTime duty = new DutyTime();
        OnDutyDoctors duty_doc = new OnDutyDoctors();
        OnDutyNurses duty_nur = new OnDutyNurses();
        OnDutyWorkers duty_wor = new OnDutyWorkers();

        duty.addDutyTime("17/12/2020", "7");
        duty_doc.addDoctorDutyTime("1", "1");
        duty_doc.addDoctorDutyTime("2", "1");
        duty_doc.addDoctorDutyTime("3", "1");
        duty_doc.addDoctorDutyTime("4", "1");

        duty_nur.addNurseDutyTime("6", "1");
        duty_wor.addWorkerDutyTime("7", "1");

    }

    public void insertCardiologists() throws SQLException , ClassNotFoundException{
        Doctor doctor = new Doctor();
        doctor.addDoctor("chatz", "manos123", "Manos", "Chatzakis", "Hrakleio", "chatzakis@tep.gr", "null", "cardiologist", "AT5555");
        doctor.addDoctor("gchatz", "gio123", "George", "Chatzakis", "Hrakleio", "chatzakis_george@tep.gr", "null", "cardiologist", "AT1019");
        doctor.addDoctor("kourl", "kourl123", "Marilena", "Kourletaki", "Hrakleio", "kourl@tep.gr", "null", "cardiologist", "AT2029");
        doctor.addDoctor("katechatz", "kate123", "Katerina", "Chatzaki", "Hrakleio", "kate_ch@tep.gr", "null", "cardiologist", "UT7465");
    }

    public void insertGynecologists() throws SQLException , ClassNotFoundException{
        Doctor doctor = new Doctor();
        doctor.addDoctor("georgegk", "george123", "George", "Kokolakis", "Hrakleio", "kokol@tep.gr", "null", "gynecologist", "KL3409");
        doctor.addDoctor("nikoltab", "nikolas321", "Nikolas", "Taboukos", "Hrakleio", "tabouk@tep.gr", "null", "gynecologist", "PI9987");
        doctor.addDoctor("leutSpinth", "leut123", "Leuteris", "Spinthakis", "Hrakleio", "efraim@tep.gr", "null", "gynecologist", "K75367");
    }

    public void insertPathologists() throws SQLException , ClassNotFoundException{
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

    public void insertPulmonologists() throws SQLException , ClassNotFoundException{
        Doctor doctor = new Doctor();
        doctor.addDoctor("gfou", "gfou123", "Giorgos", "Fountakis", "Hrakleio", "fount@tep.gr", "null", "pulmonologist", "UT4629");
        doctor.addDoctor("vasilias", "vasilik33", "Nikos", "Vasilikopoulos", "Hrakleio", "vasilik@tep.gr", "null", "pulmonologist", "GF9078");
        doctor.addDoctor("gmallis", "mall123", "Giorgos", "Mallis", "Hrakleio", "mallis@tep.gr", "null", "pulmonologist", "TR5423");
    }

    public void insertMedicals() throws SQLException, ClassNotFoundException {
        Medical med = new Medical();
        med.addMedical("covid", "1", "8", "1", "6", "17/12/2020");
    }

    public void insertPatients() throws SQLException , ClassNotFoundException{
        Patient pat = new Patient();
        pat.addPatient("nickLen", "len123", "Nikos", "Lenakis", "Hrakleio", "len@gmail.com", "null", "16/11/1990", "12066455", "IK8986", "IKA");
        pat.addPatient("gPer", "gper123", "Giorgos", "Perakis", "Hrakleio", "per@gmail.com", "null", "12/9/1980", "97046412", "KH6754", "IKA");
        pat.addPatient("gIwan", "iwannou21", "Giannis", "Iwannou", "Hrakleio", "iwannou@gmail.com", "null", "12/03/1970", "31155415", "UT1213", "IKA");
        pat.addPatient("pPetr", "pav123", "Pavlos", "Petrakis", "Hrakleio", "petrakis_pav@gmail.com", "null", "02/03/1995", "80048812", "BG7813", "IKA");
    }

    public void insertVisit() throws SQLException , ClassNotFoundException{
        Visit vis = new Visit();
        vis.addVisit("2020", "1", "8");
        vis.addSymptom("1", "Piretos");
    }

    public void insertInitExams() throws SQLException , ClassNotFoundException{
        Examination ex = new Examination();
        ex.addInitialExam("8", "1", "null", "null", "1", "17/12/2020");
    }

    public void insertReExams() throws SQLException , ClassNotFoundException{
        Examination ex = new Examination();
        ex.addReExam("8", "1", "1", "1", "1", "17/12/2020", "1", false);
    }
}
