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
import java.sql.SQLException;

/**
 *
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author George Kokolakis (gkokol@ics.forth.gr)
 */
public class DBDataGenerator {

    public void insertData() throws SQLException {
        insertDoctors();
        insertNurses();
        insertCoordinators();
        insertIllnesses();
        insertDrugs();
        insertDuties();
        insertPatients();
        insertVisit();
        insertInitExams();
        insertMedicals();
        insertReExams();

    }

    public void insertDoctors() throws SQLException {
        insertCardiologists();
        insertPathologists();
        insertEndocrinologists();
        insertGynecologists();
        insertPulmonologists();
    }

    public void insertNurses() throws SQLException {
        Nurse nurse = new Nurse();
        nurse.addNurse("evach", "evach123", "Eva", "Chamilaki", "Hrakleio", "ch@tep.gr", "null", "AT1010");
        /*nurse.addNurseByID("Eva", "Chamilaki", "Address", "cham@tep.gr", "null");
        nurse.addNurseByID("Katerina", "Petraki", "Address", "pet@tep.gr", "null");
        nurse.addNurseByID("Olimpia", "Popa", "Address", "popa@tep.gr", "null");
        nurse.addNurseByID("Katerina", "Chatzaki", "Address", "katechatz@tep.gr", "null");
        nurse.addNurseByID("Theodora", "Sampaloy", "Address", "samp@tep.gr", "null");*/

    }

    public void insertCoordinators() throws SQLException {
        Coordinator coord = new Coordinator();
        coord.addCoordinator("alvin", "alvin123", "Alvi", "Nikola", "Hrakleio", "alvi@tep.gr", "null", "AN6768");
        /*coord.addCoordinatorByID("Alvi", "Nikola", "Address", "nikola@tep.gr", "null");
        coord.addCoordinatorByID("Vasilis", "Plevris", "Address", "plev@tep.gr", "null");
        coord.addCoordinatorByID("Matthaios", "Iliakis", "Address", "mat@tep.gr", "null");*/
    }

    public void insertDrugs() throws SQLException {
        Drug drug = new Drug();
        drug.addDrug("Vaccine", "Vaccine", "500mg", "1");
        drug.addDrug("Aspirin", "Pill", "500mg", "2");
        drug.addDrug("Bandage", "Applicable", "null", "3");
        drug.addDrug("AllergyCream", "Cream", "100mg", "4");
    }

    public void insertIllnesses() throws SQLException {
        Illness ill = new Illness();
        ill.addIllness("Covid");
        ill.addIllness("Flu");
        ill.addIllness("Bone_Break");
        ill.addIllness("Allergy");
    }

    public void insertDuties() throws SQLException {
        DutyTime duty = new DutyTime();
        duty.addDutyTime("17/12/2020", "7");
    }

    public void insertCardiologists() throws SQLException {
        Doctor doctor = new Doctor();
        /*doctor.addDoctorByID("cardiologist", "Manos", "Chatzakis", "Address", "chatzakis@tep.gr", "null");
        doctor.addDoctorByID("cardiologist", "Nikos", "Fanourakis", "Address", "fan@tep.gr", "null");
        doctor.addDoctorByID("cardiologist", "Giannis", "Mitakis", "Address", "mit@tep.gr", "null");
        doctor.addDoctorByID("cardiologist", "Mixalis", "Spanakis", "Address", "span@tep.gr", "null");
        doctor.addDoctorByID("cardiologist", "Manos", "Papadakis", "Address", "pap@tep.gr", "null");*/
        doctor.addDoctor("chatz", "manos123", "Manos", "Chatzakis", "Hrakleio", "chatzakis@tep.gr", "null", "cardiologist", "AT5555");
    }

    public void insertGynecologists() throws SQLException {
        Doctor doctor = new Doctor();
        /*doctor.addDoctorByID("gynecologist", "Nikos", "Kontonasios", "Address", "konto@tep.gr", "null");
        doctor.addDoctorByID("gynecologist", "Giwrgos", "Fountakis", "Address", "fount@tep.gr", "null");
        doctor.addDoctorByID("gynecologist", "Nikos", "Vasilikopoulos", "Address", "vasilik@tep.gr", "null");*/
        doctor.addDoctor("georgegk", "george123", "George", "Kokolakis", "Hrakleio", "kokol@tep.gr", "null", "gynecologist", "KL3409");

    }

    public void insertPathologists() throws SQLException {
        Doctor doctor = new Doctor();
        /*doctor.addDoctorByID("pathologist", "Giwrgos", "Kokolakis", "Address", "giwrgos@tep.gr", "null");
        doctor.addDoctorByID("pathologist", "Drosos", "Drosakis", "Address", "drosos@tep.gr", "null");
        doctor.addDoctorByID("pathologist", "Nikolas", "Taboukos", "Address", "tab@tep.gr", "null");
        doctor.addDoctorByID("pathologist", "Lefteris", "Spinthakis", "Address", "spith@tep.gr", "null");*/
        doctor.addDoctor("drosos", "drosos123", "Drosos", "Drosakis", "Hrakleio", "drosos@tep.gr", "null", "pathologist", "AE2359");

    }

    public void insertEndocrinologists() throws SQLException {
        Doctor doctor = new Doctor();
        /*doctor.addDoctorByID("endocrinologist", "Elvira", "Sakoudi", "Address", "sak@tep.gr", "null");
        doctor.addDoctorByID("endocrinologist", "Dimita", "Xristodoulou", "Address", "xr@tep.gr", "null");
        doctor.addDoctorByID("endocrinologist", "Effie", "Probona", "Address", "prob@tep.gr", "null");
        doctor.addDoctorByID("endocrinologist", "Giannis", "Kaziales", "Address", "kaz@tep.gr", "null");*/
        doctor.addDoctor("elvis", "elvis123", "Elvira", "Sakoudi", "Hrakleio", "sak@tep.gr", "null", "endocrinologist", "MN8866");

    }

    public void insertPulmonologists() throws SQLException {
        Doctor doctor = new Doctor();
        /*doctor.addDoctorByID("pulmonologist", "Giannis", "Mutakis", "Address", "giannis@tep.gr", "null");
        doctor.addDoctorByID("pulmonologist", "Giorgos", "Chatzakis", "Address", "chatzg@tep.gr", "null");
        doctor.addDoctorByID("pulmonologist", "Paulos", "Iwanidis", "Address", "paulos@tep.gr", "null");
        doctor.addDoctorByID("pulmonologist", "Giannis", "Tzitzikas", "Address", "tzitzik@tep.gr", "null");
        doctor.addDoctorByID("pulmonologist", "Giannis", "Marketakis", "Address", "market@tep.gr", "null");*/
        doctor.addDoctor("gfou", "gfou123", "Giorgos", "Fountakis", "Hrakleio", "fount@tep.gr", "null", "pulmonologist", "UT4629");

    }

    public void insertMedicals() throws SQLException {
        Medical med = new Medical();
        /*med.addMedicalByID("covid_test", "1", "1", "1", "26/12/2020");
        med.addMedicalByID("covid_test", "2", "1", "1", "27/12/2020");
        med.addMedicalByID("xRay_test", "2", "1", "1", "28/12/2020");
        med.addMedicalByID("xRay_test", "3", "1", "1", "28/12/2020");
        med.addMedicalByID("blood_test", "1", "1", "1", "28/12/2020");*/
        //med.addMedical("covid", "1", "1", "1", "1", "17/12/2020");
        med.addMedical("covid", "1","8", "1","6", "17/12/2020");
    }

    public void insertPatients() throws SQLException {
        Patient pat = new Patient();
        pat.addPatient("nickLen", "len123", "Nikos", "Lenakis", "Hrakleio", "len@gmail.com", "null", "16/11/1990", "12066455", "IK8986", "IKA");
        /*pat.addPatientByID("Giannis", "Papadakis", "Address", "g@gmail.com", "null", "30", "12121212121", "IKA");
        pat.addChronicDisease("1", "Asthma");

        pat.addPatientByID("Nikos", "Liontakis", "Address", "g@gmail.com", "null", "30", "12121212121", "IKA");
        pat.addChronicDisease("2", "Autoanoso");

        pat.addPatientByID("Manos", "Giorgakis", "Address", "giorg@gmail.com", "null", "14", "12121212020", "IKA");
        pat.addPatientByID("Makis", "Kotsabasis", "Address", "kotsa@gmail.com", "null", "60", "12121219090", "IKA");
        pat.addPatientByID("Pavlos", "Giannakis", "Address", "ginnn@gmail.com", "null", "11", "12121218989", "IKA");*/
    }

    public void insertVisit() throws SQLException {
        Visit vis = new Visit();
        vis.addVisit("2020", "1", "8");
        vis.addSymptom("1", "Piretos");
    }

    public void insertInitExams() throws SQLException {
        Examination ex = new Examination();
        ex.addInitialExam("8", "1", "null", "null", "1", "17/12/2020");
        //ex.addReExam("1", "1", "1", "1", "1", "17/12/2020", "1", "true");
    }

    public void insertReExams() throws SQLException {
        Examination ex = new Examination();
        ex.addReExam("8", "1", "1", "1", "1", "17/12/2020", "1", true);
    }
}
