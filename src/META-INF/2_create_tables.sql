drop table if exists `loosebox_pdm`.rank;
create table `loosebox_pdm`.rank (
    rankid SMALLINT(2) NOT NULL PRIMARY KEY,
    rank VARCHAR(50) NOT NULL,
    abbreviation VARCHAR(25) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.rank VALUES(1, 'Marshal of the Nigerian Air Force', 'MNAF');
insert into `loosebox_pdm`.rank VALUES(2, 'Air Chief Marshal', 'Air Chf Mshl');
insert into `loosebox_pdm`.rank VALUES(3, 'Air Marshal', 'Air Mshl');
insert into `loosebox_pdm`.rank VALUES(4, 'Air Vice Marshal', 'AVM');
insert into `loosebox_pdm`.rank VALUES(5, 'Air Commodore', 'Air Cdre');
insert into `loosebox_pdm`.rank VALUES(6, 'Group Captain', 'Gp Capt');
insert into `loosebox_pdm`.rank VALUES(7, 'Wing Commander', 'Wg Cdr');
insert into `loosebox_pdm`.rank VALUES(8, 'Squadron Leader', 'Sqn Ldr');
insert into `loosebox_pdm`.rank VALUES(9, 'Flight Lieutenant', 'Flt Lt');
insert into `loosebox_pdm`.rank VALUES(10, 'Flying Officer', 'Fg Offr');
insert into `loosebox_pdm`.rank VALUES(11, 'Pilot Officer', 'Plt Offr');
insert into `loosebox_pdm`.rank VALUES(12, 'Air Warrant Officer', 'AWO');
insert into `loosebox_pdm`.rank VALUES(13, 'Master Warrant Officer', 'MWO');
insert into `loosebox_pdm`.rank VALUES(14, 'Warrant Officer', 'WO');
insert into `loosebox_pdm`.rank VALUES(15, 'Flight Seargent', 'FS');
insert into `loosebox_pdm`.rank VALUES(16, 'Seargent', 'Sgt');
insert into `loosebox_pdm`.rank VALUES(17, 'Corporal', 'Cpl');
insert into `loosebox_pdm`.rank VALUES(18, 'Lance Corporal', 'LCpl');
insert into `loosebox_pdm`.rank VALUES(19, 'Aircraftman', 'ACM');

drop table if exists `loosebox_pdm`.gender;
create table `loosebox_pdm`.gender (
    genderid SMALLINT(2) NOT NULL PRIMARY KEY,
    gender VARCHAR(6) NOT NULL,
    abbreviation VARCHAR(6) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.gender VALUES(1, 'Male', 'M');
insert into `loosebox_pdm`.gender VALUES(2, 'Female', 'F');

drop table if exists `loosebox_pdm`.stateoforigin;
create table `loosebox_pdm`.stateoforigin (
    stateoforiginid SMALLINT(2) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    stateoforigin VARCHAR(50) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists `loosebox_pdm`.localgovernmentarea;
create table `loosebox_pdm`.localgovernmentarea (
    localgovernmentareaid INTEGER(4) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    stateoforigin SMALLINT(2) NOT NULL,
    localgovernmentarea VARCHAR(50) NOT NULL,

    FOREIGN KEY (stateoforigin) REFERENCES `loosebox_pdm`.stateoforigin(stateoforiginid) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists `loosebox_pdm`.unittype;
create table `loosebox_pdm`.unittype (
    unittypeid SMALLINT(2) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    unittype VARCHAR(100) NOT NULL,
    abbreviation VARCHAR(50) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
--- School, Center, Group, Depot
insert into `loosebox_pdm`.unittype VALUES(1, 'National', 'National');
insert into `loosebox_pdm`.unittype VALUES(2, 'Ministry of Defence', 'MOD');
insert into `loosebox_pdm`.unittype VALUES(3, 'Tri-Service Unit', 'Tri-Svc Unit');
insert into `loosebox_pdm`.unittype VALUES(4, 'Office of the Chief of Air Staff', 'Office of the CAS');
insert into `loosebox_pdm`.unittype VALUES(5, 'HQ NAF Staff Branch', 'Branch');
insert into `loosebox_pdm`.unittype VALUES(6, 'Command', 'Comd');
insert into `loosebox_pdm`.unittype VALUES(7, 'Direct Reporting Unit', 'DRU');
insert into `loosebox_pdm`.unittype VALUES(8, 'Directorate', 'Dir');
insert into `loosebox_pdm`.unittype VALUES(9, 'Group', 'Gp');
insert into `loosebox_pdm`.unittype VALUES(10, 'Wing', 'Wg');
insert into `loosebox_pdm`.unittype VALUES(11, 'Squadron', 'Sqn');
insert into `loosebox_pdm`.unittype VALUES(12, 'Flight', 'Flt');
insert into `loosebox_pdm`.unittype VALUES(13, 'Section', 'Section');
insert into `loosebox_pdm`.unittype VALUES(14, 'Special Instructions List', 'SI List');

drop table if exists `loosebox_pdm`.unit;
create table `loosebox_pdm`.unit
(
    unitid INTEGER(8) AUTO_INCREMENT not null primary key,
    unit VARCHAR(100) not null UNIQUE,
    unittype SMALLINT(2) not null,
    abbreviation VARCHAR(50) not null UNIQUE,
    parentunit INTEGER(8) null,
    unitsortorder INTEGER(8) null,
    
    FOREIGN KEY (unittype) REFERENCES `loosebox_pdm`.unittype(unittypeid) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (parentunit) REFERENCES `loosebox_pdm`.unit(unitid) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists `loosebox_pdm`.appointmenttype;
create table `loosebox_pdm`.appointmenttype (
    appointmenttypeid SMALLINT(2) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    appointmenttype VARCHAR(100) NOT NULL,
    abbreviation VARCHAR(50) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.appointmenttype VALUES(1, 'Defence Chief', 'Defence Chief');
insert into `loosebox_pdm`.appointmenttype VALUES(2, 'Service Chief', 'Svc Chief');
insert into `loosebox_pdm`.appointmenttype VALUES(3, 'Tri-Service', 'Tri-Svc');
insert into `loosebox_pdm`.appointmenttype VALUES(4, 'Branch Chief', 'Branch Chief');
insert into `loosebox_pdm`.appointmenttype VALUES(5, 'General/Flag/Air Officer Commanding', 'GOC/FOC/AOC');
insert into `loosebox_pdm`.appointmenttype VALUES(6, 'Group Managing Director', 'GMD');
insert into `loosebox_pdm`.appointmenttype VALUES(7, 'Commandant', 'Comdt');
insert into `loosebox_pdm`.appointmenttype VALUES(8, 'Deputy Commandant', 'Dy Comdt');
insert into `loosebox_pdm`.appointmenttype VALUES(9, 'Managing Director', 'MD');
insert into `loosebox_pdm`.appointmenttype VALUES(10, 'Principal Officer', 'PO');
insert into `loosebox_pdm`.appointmenttype VALUES(11, 'Principal Staff Officer', 'PSO');
insert into `loosebox_pdm`.appointmenttype VALUES(12, 'Director', 'Dir');
insert into `loosebox_pdm`.appointmenttype VALUES(13, 'Commander', 'Comd');
insert into `loosebox_pdm`.appointmenttype VALUES(14, 'Deputy Director', 'Dy Dir');
insert into `loosebox_pdm`.appointmenttype VALUES(15, 'Command Specialist Officer', 'Comd Specialist Offr');
insert into `loosebox_pdm`.appointmenttype VALUES(16, 'Military/Air/Naval Assistant', 'MA/AA/NA');
insert into `loosebox_pdm`.appointmenttype VALUES(17, 'Service Headqaurters Staff Officer', 'Service HQ Staff Offr');
insert into `loosebox_pdm`.appointmenttype VALUES(18, 'Command Staff Officer', 'Comd Staff Offr');
insert into `loosebox_pdm`.appointmenttype VALUES(19, 'Security Officer', 'Sy Offr');
insert into `loosebox_pdm`.appointmenttype VALUES(20, 'Commanding Officer', 'CO');
insert into `loosebox_pdm`.appointmenttype VALUES(21, 'Aide de Camp', 'ADC');
insert into `loosebox_pdm`.appointmenttype VALUES(22, 'Officer Commanding', 'OC');
insert into `loosebox_pdm`.appointmenttype VALUES(23, 'Platoon/Flight Commander', 'Platoon/Flt Comd');
insert into `loosebox_pdm`.appointmenttype VALUES(24, 'Admin Officer', 'AO');
insert into `loosebox_pdm`.appointmenttype VALUES(25, 'OTHER - Officer', 'OTHER - Offr');
insert into `loosebox_pdm`.appointmenttype VALUES(26, 'Army/Navy/Air Warrant Officer', 'AWO/NWO');
insert into `loosebox_pdm`.appointmenttype VALUES(27, 'Regimental Seargent Major', 'RSM');
insert into `loosebox_pdm`.appointmenttype VALUES(28, 'Chief Clerk', 'CC');
insert into `loosebox_pdm`.appointmenttype VALUES(29, 'Supervisor', 'Supvr');
insert into `loosebox_pdm`.appointmenttype VALUES(30, 'Personal Assistant', 'PA');
insert into `loosebox_pdm`.appointmenttype VALUES(31, 'Driver', 'Dvr');
insert into `loosebox_pdm`.appointmenttype VALUES(32, 'Office Assistant', 'Office Asst');
insert into `loosebox_pdm`.appointmenttype VALUES(33, 'Orderly', 'Orderly');
insert into `loosebox_pdm`.appointmenttype VALUES(34, 'OTHER - Soldier/Rating/Airman', 'OTHER - Soldier/Rating/Airman');

drop table if exists `loosebox_pdm`.appointment;
create table `loosebox_pdm`.appointment
(
    appointmentid INTEGER(8) AUTO_INCREMENT not null primary key,
    appointment VARCHAR(100) not null UNIQUE,
    appointmenttype SMALLINT(2) not null,
    abbreviation VARCHAR(50) not null UNIQUE,
    parentappointment INTEGER(8) null,

    FOREIGN KEY (appointmenttype) REFERENCES `loosebox_pdm`.appointmenttype(appointmenttypeid) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (parentappointment) REFERENCES `loosebox_pdm`.appointment(appointmentid) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.appointment VALUES(1, 'Chief of Defence Staff', 1, 'CDS', null);
insert into `loosebox_pdm`.appointment VALUES(2, 'Chief of the Air Staff', 2, 'CAS', 1);
insert into `loosebox_pdm`.appointment VALUES(3, 'Chief of Policy & Plans', 4, 'COPP', 2);
insert into `loosebox_pdm`.appointment VALUES(4, 'Chief of Training & Operations', 4, 'CTOP', 2);
insert into `loosebox_pdm`.appointment VALUES(5, 'Chief of Aircraft Engineering', 4, 'CAcE', 2);
insert into `loosebox_pdm`.appointment VALUES(6, 'Chief of Logistics & Communications', 4, 'CLog & Comms', 2);
insert into `loosebox_pdm`.appointment VALUES(7, 'Chief of Administration', 4, 'COA', 2);
insert into `loosebox_pdm`.appointment VALUES(8, 'Chief of Standard Evaluation', 4, 'COSE', 2);
insert into `loosebox_pdm`.appointment VALUES(9, 'Chief of Medical Services', 4, 'CMS', 2);
insert into `loosebox_pdm`.appointment VALUES(10, 'Air Secretary', 4, 'Air Sec', 2);
insert into `loosebox_pdm`.appointment VALUES(11, 'Chief of Accounts and Budget', 4, 'CAB', 2);
insert into `loosebox_pdm`.appointment VALUES(12, 'Principal Air Staff Officer to the Chief of Air Staff', 11, 'PASO-CAS', 2);
insert into `loosebox_pdm`.appointment VALUES(13, 'Air Assistant to the Chief of Air Staff', 16, 'AA-CAS', 2);
insert into `loosebox_pdm`.appointment VALUES(14, 'Air Officer Commanding Tactical Air Command', 5, 'AOC TAC', 2);
insert into `loosebox_pdm`.appointment VALUES(15, 'Air Officer Commanding Special Operations Command', 5, 'AOC SOC', 2);
insert into `loosebox_pdm`.appointment VALUES(16, 'Air Officer Commanding Mobility Command', 5, 'AOC MC', 2);
insert into `loosebox_pdm`.appointment VALUES(17, 'Air Officer Commanding Training Command', 5, 'AOC TC', 2);
insert into `loosebox_pdm`.appointment VALUES(18, 'Air Officer Commanding Logistics Command', 5, 'AOC LC', 2);
insert into `loosebox_pdm`.appointment VALUES(19, 'Project Implementation and Monitoring Team Coordinator', 10, 'PIMT Coord', 2);
insert into `loosebox_pdm`.appointment VALUES(20, 'Commandant Air Force Institute of Technology', 7, 'Comdt AFIT', 2);
insert into `loosebox_pdm`.appointment VALUES(21, 'Group Managing Director Nigerian Air Force Holding Company', 6, 'GMD NAFHC', 7);
insert into `loosebox_pdm`.appointment VALUES(22, 'Managing Director Nigerian Air Force Housing and Construction Company', 9, 'MD NAFHCC', 21);
insert into `loosebox_pdm`.appointment VALUES(23, 'Managing Director Nigerian Air Force Properties', 9, 'MD NAFPROP', 21);
insert into `loosebox_pdm`.appointment VALUES(24, 'Managing Director Nigerian Air Force Investments Limited', 9, 'MD NAFIL', 21);
insert into `loosebox_pdm`.appointment VALUES(25, 'Managing Director Aeronautical Engineering and Technical Services Limited', 9, 'MD AETSL', 21);
insert into `loosebox_pdm`.appointment VALUES(26, 'Commandant National Air Defence Corp', 7, 'Comdt NADC', 1);
insert into `loosebox_pdm`.appointment VALUES(27, 'Commandant Air War College', 7, 'Comdt AWC', 2);
insert into `loosebox_pdm`.appointment VALUES(28, 'NAFWC', 1, 'NAFWC', 1);
insert into `loosebox_pdm`.appointment VALUES(29, 'Commandant Nigerian Armed Forces Resettlement Center', 7, 'Comdt NAFRC', 1);
insert into `loosebox_pdm`.appointment VALUES(30, 'Commander Nigerian Air Force Mother & Child Hospital', 13, 'Comd NAF M&C Hosp', 2);
insert into `loosebox_pdm`.appointment VALUES(31, 'Principal Officer Air Force Research and Development Center', 10, 'PO AFRDC', 2);
insert into `loosebox_pdm`.appointment VALUES(32, 'Commander Nigerian Air Force School of Finance and Administration', 13, 'Comd NAFSFA', 17);
insert into `loosebox_pdm`.appointment VALUES(33, 'Principal Officer Air Expo and International Liaison', 10, 'PO AILS', 2);
insert into `loosebox_pdm`.appointment VALUES(34, 'Commandant Ground Training Center', 7, 'Comdt GTC', 17);
insert into `loosebox_pdm`.appointment VALUES(35, 'Commandant Regiment Training Center', 7, 'Comdt RTC', 17);
insert into `loosebox_pdm`.appointment VALUES(36, 'NAFIS', 1, 'NAFIS', 1);
insert into `loosebox_pdm`.appointment VALUES(37, 'NAFIAM', 1, 'NAFIAM', 1);
insert into `loosebox_pdm`.appointment VALUES(38, 'NAF SMS & AVN MED', 1, 'NAF SMS & AVN MED', 1);
insert into `loosebox_pdm`.appointment VALUES(39, 'Commandant Nigerian Air Force School of Air Intelligence', 7, 'Comdt NAFSAINT', 17);
insert into `loosebox_pdm`.appointment VALUES(40, 'Commander 013 Quick Response Force Minna', 13, 'Comd 013 QRF', 2);
insert into `loosebox_pdm`.appointment VALUES(41, 'Commander 015 Special Investigations Group', 13, 'Comd 015 SIG', 2);
insert into `loosebox_pdm`.appointment VALUES(42, 'Commander 041 Communications Depot', 13, 'Comd 041 CD', 2);
insert into `loosebox_pdm`.appointment VALUES(43, 'Commander 051 Personnel Management Group', 13, 'Comd 051 PMG', 2);
insert into `loosebox_pdm`.appointment VALUES(44, 'Commander 053 Headquarters Nigerian Air Force Camp', 13, 'Comd 053 HQ NAF CAMP', 2);
insert into `loosebox_pdm`.appointment VALUES(45, 'Commander 055 Headquarters Nigerian Air Force Camp', 13, 'Comd 055 HQ NAF CAMP', 2);
insert into `loosebox_pdm`.appointment VALUES(46, 'Commander 057 Provost Investigation Group', 13, 'Comd 057 PIG', 2);
insert into `loosebox_pdm`.appointment VALUES(47, 'Commander 061 AMC', 1, 'Comd 061 AMC', 1);
insert into `loosebox_pdm`.appointment VALUES(48, 'Commander 063 Nigerian Air Force Hospital', 13, 'Comd 063 NAFH', 2);
insert into `loosebox_pdm`.appointment VALUES(49, 'Commander 081 Pay and Accounting Group', 13, 'Comd 081 PAG', 2);
--insert into `loosebox_pdm`.appointment VALUES(14, 'Director of Procurement', 9, 'DProc', 6);

drop table if exists `loosebox_pdm`.commissiontype;
create table `loosebox_pdm`.commissiontype (
    commissiontypeid SMALLINT(2) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    commissiontype VARCHAR(100) NOT NULL,
    abbreviation VARCHAR(50) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.commissiontype VALUES(1, 'Regular Commission', 'RC');
insert into `loosebox_pdm`.commissiontype VALUES(2, 'Direct Regular Commission', 'DRC');
insert into `loosebox_pdm`.commissiontype VALUES(3, 'Direct Short Service Commission', 'DSSC');
insert into `loosebox_pdm`.commissiontype VALUES(4, 'Branch', 'Branch');

drop table if exists `loosebox_pdm`.speciality;
create table `loosebox_pdm`.speciality (
    specialityid SMALLINT(2) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    speciality VARCHAR(100) NOT NULL,
    abbreviation VARCHAR(50) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.speciality VALUES(1, 'Pilot', 'Plt');
insert into `loosebox_pdm`.speciality VALUES(2, 'Navigator', 'Nav');
insert into `loosebox_pdm`.speciality VALUES(3, 'Air Traffic Controller', 'ATC');
insert into `loosebox_pdm`.speciality VALUES(4, 'Aircraft Engineer', 'Ac Engr');
insert into `loosebox_pdm`.speciality VALUES(5, 'Intelligence', 'Int');
insert into `loosebox_pdm`.speciality VALUES(6, 'Regiment', 'Regt');
insert into `loosebox_pdm`.speciality VALUES(7, 'Communications Engineering', 'CE');
insert into `loosebox_pdm`.speciality VALUES(8, 'Radar', 'Radar');

drop table if exists `loosebox_pdm`.trade;
create table `loosebox_pdm`.trade (
    tradeid SMALLINT(2) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    trade VARCHAR(100) NOT NULL,
    abbreviation VARCHAR(50) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.trade VALUES(1, 'No Trade', 'NT');
insert into `loosebox_pdm`.trade VALUES(2, 'Load Master', 'Load Master');
insert into `loosebox_pdm`.trade VALUES(3, 'Aircraft Technician', 'Ac TEch');
insert into `loosebox_pdm`.trade VALUES(4, 'Intelligence Operator', 'Int Optr');
insert into `loosebox_pdm`.trade VALUES(5, 'Regiment', 'Regt');
insert into `loosebox_pdm`.trade VALUES(6, 'Communications Technician', 'CE Tech');
insert into `loosebox_pdm`.trade VALUES(7, 'Radar Technician', 'Radar Tech');


drop table if exists `loosebox_pdm`.grade;
create table `loosebox_pdm`.grade (
    gradeid SMALLINT(2) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    grade VARCHAR(5) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.grade VALUES(1, 'NT');
insert into `loosebox_pdm`.grade VALUES(2, 'X1');
insert into `loosebox_pdm`.grade VALUES(3, 'A1');
insert into `loosebox_pdm`.grade VALUES(4, 'X2');
insert into `loosebox_pdm`.grade VALUES(5, 'A2');
insert into `loosebox_pdm`.grade VALUES(6, 'X3');
insert into `loosebox_pdm`.grade VALUES(7, 'A3');

drop table if exists `loosebox_pdm`.personneldata;
create table `loosebox_pdm`.personneldata
(
    personneldataid INTEGER(8) AUTO_INCREMENT not null primary key,
    servicenumber VARCHAR(24) not null UNIQUE,
    rank SMALLINT(2) not null,
    firstname VARCHAR(100) not null,
    middlename VARCHAR(100) null,
    surname VARCHAR(100) not null,
    seniority DATE null,
    dateofbirth DATE null,
    gender SMALLINT(2) not null,
    localgovernmentarea INTEGER(4) null,
    mobilephonenumber1 VARCHAR(14) null,
    mobilephonenumber2 VARCHAR(14) null,
    emailaddress VARCHAR(100) null,

    FOREIGN KEY (rank) REFERENCES `loosebox_pdm`.rank(rankid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (gender) REFERENCES `loosebox_pdm`.gender(genderid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (localgovernmentarea) REFERENCES `loosebox_pdm`.localgovernmentarea(localgovernmentareaid) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists `loosebox_pdm`.officersdata;
create table `loosebox_pdm`.officersdata
(
    officersdataid INTEGER(8) AUTO_INCREMENT not null primary key,
    personneldata INTEGER(8) not null UNIQUE,
    courseonentry VARCHAR(20) null,
    dateofcommission DATE null,
    commissiontype SMALLINT(2) null,
    speciality SMALLINT(2) null,

    FOREIGN KEY (personneldata) REFERENCES `loosebox_pdm`.personneldata(personneldataid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (commissiontype) REFERENCES `loosebox_pdm`.commissiontype(commissiontypeid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (speciality) REFERENCES `loosebox_pdm`.speciality(specialityid) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists `loosebox_pdm`.airmansdata;
create table `loosebox_pdm`.airmansdata
(
    airmansdataid INTEGER(8) AUTO_INCREMENT not null primary key,
    personneldata INTEGER(8) not null UNIQUE,
    dateofenlistment DATE null,
    trade SMALLINT(2) null,
    grade SMALLINT(2) null,

    FOREIGN KEY (personneldata) REFERENCES `loosebox_pdm`.personneldata(personneldataid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (trade) REFERENCES `loosebox_pdm`.trade(tradeid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (grade) REFERENCES `loosebox_pdm`.grade(gradeid) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists `loosebox_pdm`.personnelposting;
create table `loosebox_pdm`.personnelposting
(
    personnelpostingid INTEGER(8) AUTO_INCREMENT not null primary key,
    personneldata INTEGER(8) not null,
    unit INTEGER(8) not null,
    appointment INTEGER(8) null,
    datetakenonstrength DATE null, 

    FOREIGN KEY (personneldata) REFERENCES `loosebox_pdm`.personneldata(personneldataid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (unit) REFERENCES `loosebox_pdm`.unit(unitid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (appointment) REFERENCES `loosebox_pdm`.appointment(appointmentid) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists `loosebox_pdm`.qualification;
create table `loosebox_pdm`.qualification (
    qualificationid INTEGER(8) AUTO_INCREMENT not null primary key,
    qualification VARCHAR(255) NOT NULL,
    description VARCHAR(1000) NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists `loosebox_pdm`.course;
create table `loosebox_pdm`.course (
    courseid INTEGER(8) AUTO_INCREMENT not null primary key,
    coursetitle VARCHAR(255) NOT NULL,
    qualification INTEGER(8) NULL,
    location VARCHAR(100) NULL,
    startdate DATE NULL,
    enddate DATE NULL,
    description VARCHAR(1000) NULL,

    FOREIGN KEY (qualification) REFERENCES `loosebox_pdm`.qualification(qualificationid) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists `loosebox_pdm`.courseattended;
create table `loosebox_pdm`.courseattended (
    courseattendedid INTEGER(8) AUTO_INCREMENT not null primary key,
    personneldata INTEGER(8) NOT NULL,
    course INTEGER(8) NOT NULL,

    FOREIGN KEY (personneldata) REFERENCES `loosebox_pdm`.personneldata(personneldataid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (course) REFERENCES `loosebox_pdm`.course(courseid) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;



