SELECT DISTINCT patients.patient_id, patients.name, patients.surname, patients.birth_date, visit_symptoms.symptom
FROM patients
INNER JOIN visit ON visit.patient_id = patients.patient_id
INNER JOIN visit_symptoms ON visit_symptoms.visit_id = visit.visit_id
INNER JOIN dutytime ON dutytime.dutytime_id = visit.dutytime_id
WHERE dutytime.dutytime_id = 1;




SELECT visit.visit_id, examinations.exam_id, drugs.name AS drug_name, illnesses.name, patients.name, patients.surname, patients.patient_id
FROM examinations
LEFT JOIN drugs ON examinations.drug_id = drugs.drug_id
LEFT JOIN illnesses ON drugs.illness_id = illnesses.illness_id
INNER JOIN patients ON patients.patient_id =    examinations.patient_id
INNER JOIN visit ON visit.visit_id = examinations.visit_id
WHERE visit.dutytime_id = 2



SELECT *
FROM patients
INNER JOIN visit ON visit.patient_id = patients.patient_id
WHERE visit.dutytime_id = 2




SELECT dutytime.date
FROM dutytime
INNER JOIN doctor_duties ON dutytime.dutytime_id = doctor_duties.dutytime_id
WHERE doctor_duties.doctor_id = 1

/*ΤΟ ΚΑΛΟ ΓΙΑ ΤΑ MEDICALS*/
SELECT visit.visit_id, patients.patient_id, visit.dutytime_id, medicals.medical_id
FROM patients
INNER JOIN visit ON visit.patient_id = patients.patient_id
INNER JOIN examinations ON examinations.visit_id = visit.visit_id
INNER JOIN medicals ON medicals.exam_id = examinations.exam_id
WHERE visit.dutytime_id = 1

/*To kalo gia ta exams*/
SELECT visit.visit_id, patients.patient_id, visit.dutytime_id,examinations.exam_id
FROM patients
INNER JOIN visit ON visit.patient_id = patients.patient_id
INNER JOIN examinations ON examinations.visit_id = visit.visit_id
WHERE visit.dutytime_id = 1

/*To kalo gia tous pats xwris ta simptwmata..*/
SELECT visit.visit_id, visit.date, patients.patient_id, patients.name, patients.surname, patients.birth_date, patients.amka
FROM patients
INNER JOIN visit ON visit.patient_id = patients.patient_id
WHERE visit.dutytime_id = 2

SELECT visit_symptoms.symptom, visit.patient_id
FROM visit_symptoms
INNER JOIN visit ON visit.visit_id = visit_symptoms.visit_id
WHERE visit.dutytime_id = 1