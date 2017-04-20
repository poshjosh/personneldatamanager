SELECT DISTINCT t1.officersdataid AS a1, t1.course AS a2, t1.dateofcommission AS a3, 
-- Search with explicit Criteriabuilder usage. 
-- Should have yielded 2 results, but yielded 1 result, however size of 2 results. 

DISTINCT COUNT(t0.officersdataid) 
FROM stateoforigin t7, rank t6, gender t5, appointment t4, 
personneldata t3, speciality t2, commissiontype t1, officersdata t0 
WHERE (((((((((((((((((t0.course LIKE ? OR t1.abbreviation LIKE ?) OR t1.commissiontype LIKE ?) OR t2.abbreviation LIKE ?) OR t2.speciality LIKE ?) OR t3.firstname LIKE ?) OR t3.localgovernmentarea LIKE ?) OR t3.middlename LIKE ?) OR t3.servicenumber LIKE ?) OR t3.surname LIKE ?) OR t4.abbreviation LIKE ?) OR t4.appointment LIKE ?) OR t5.abbreviation LIKE ?) OR t5.gender LIKE ?) OR t6.abbreviation LIKE ?) OR t6.rank LIKE ?) OR t7.stateoforigin LIKE ?) AND (((((((t1.commissiontypeid = t0.commissiontype) AND (t2.specialityid = t0.speciality)) AND (t3.personneldataid = t0.personneldata)) AND (t4.appointmentid = t3.appointment)) AND (t5.genderid = t3.gender)) AND (t6.rankid = t3.rank)) AND (t7.stateoforiginid = t3.stateoforigin)))

SELECT DISTINCT t1.officersdataid AS a1, t1.course AS a2, t1.dateofcommission AS a3, 
t1.commissiontype AS a4, t1.speciality AS a5, t1.personneldata AS a6 
FROM commissiontype t7, rank t6, stateoforigin t5, appointment t4, gender t3, personneldata t2, officersdata t1, speciality t0 
WHERE (((((((((((((((((t1.course LIKE ? OR t7.abbreviation LIKE ?) OR t7.commissiontype LIKE ?) OR t0.abbreviation LIKE ?) OR t0.speciality LIKE ?) OR t2.firstname LIKE ?) OR t2.localgovernmentarea LIKE ?) OR t2.middlename LIKE ?) OR t2.servicenumber LIKE ?) OR t2.surname LIKE ?) OR t4.abbreviation LIKE ?) OR t4.appointment LIKE ?) OR t3.abbreviation LIKE ?) OR t3.gender LIKE ?) OR t6.abbreviation LIKE ?) OR t6.rank LIKE ?) OR t5.stateoforigin LIKE ?) AND (((((((t7.commissiontypeid = t1.commissiontype) AND (t0.specialityid = t1.speciality)) AND (t2.personneldataid = t1.personneldata)) AND (t4.appointmentid = t2.appointment)) AND (t3.genderid = t2.gender)) AND (t6.rankid = t2.rank)) AND (t5.stateoforiginid = t2.stateoforigin))) ORDER BY t1.officersdataid DESC LIMIT ?, ?

SELECT DISTINCT COUNT(t0.personneldataid) 
FROM personneldata t0, officersdata t1 
WHERE ((((((t0.servicenumber LIKE ? OR t0.firstname LIKE ?) OR t0.middlename LIKE ?) OR t0.surname LIKE ?) 
OR t0.localgovernmentarea LIKE ?) OR t1.course LIKE ?) AND (t1.personneldata = t0.personneldataid))

SELECT DISTINCT t1.personneldataid AS a1, t1.dateofbirth AS a2, t1.firstname AS a3, 
t1.localgovernmentarea AS a4, t1.middlename AS a5, t1.seniority AS a6, t1.servicenumber 
AS a7, t1.surname AS a8, t1.appointment AS a9, t1.gender AS a10, t1.rank AS a11, t1.stateoforigin AS a12 
FROM officersdata t0, personneldata t1 
WHERE ((((((t1.servicenumber LIKE ? OR t1.firstname LIKE ?) OR t1.middlename LIKE ?) OR t1.surname LIKE ?) 
OR t1.localgovernmentarea LIKE ?) OR t0.course LIKE ?) AND (t0.personneldata = t1.personneldataid)) 
ORDER BY t1.personneldataid DESC LIMIT ?, ?