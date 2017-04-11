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
    stateoforiginid SMALLINT(2) NOT NULL PRIMARY KEY,
    stateoforigin VARCHAR(25) NOT NULL
)ENGINE=INNODB;
insert into `loosebox_pdm`.stateoforigin VALUES(1, 'Abia');

drop table if exists `loosebox_pdm`.unittype;
create table `loosebox_pdm`.unittype (
    unittypeid SMALLINT(2) NOT NULL PRIMARY KEY,
    unittype VARCHAR(100) NOT NULL,
    abbreviation VARCHAR(50) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
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

    FOREIGN KEY (unittype) REFERENCES `loosebox_pdm`.unittype(unittypeid) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (parentunit) REFERENCES `loosebox_pdm`.unit(unitid) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.unit VALUES(1, 'Presidency', 1, 'Presidency', null);
insert into `loosebox_pdm`.unit VALUES(2, 'National Security Agency', 1, 'NSA', 1);
--insert into `loosebox_pdm`.unit VALUES(2, 'Presidential Air Fleet', 1, 'PAF', 1);
insert into `loosebox_pdm`.unit VALUES(3, 'Ministry of Defence', 2, 'MOD', 1);
insert into `loosebox_pdm`.unit VALUES(4, 'Defence Headquarters', 3, 'DHQ', 3);
insert into `loosebox_pdm`.unit VALUES(5, 'Office of the Chief of Air Staff', 4, 'Office of the CAS', 4);
insert into `loosebox_pdm`.unit VALUES(6, 'Policy & Plans Branch', 5, 'Pol & Plans Branch', 5);
insert into `loosebox_pdm`.unit VALUES(7, 'Training & Operations Branch', 5, 'Trg & Ops Branch', 5);
insert into `loosebox_pdm`.unit VALUES(8, 'Aircraft Engineering Branch', 5, 'AcE Branch', 5);
insert into `loosebox_pdm`.unit VALUES(9, 'Logistics & Communications Branch', 5, 'Log & Comms Branch', 5);
insert into `loosebox_pdm`.unit VALUES(10, 'Administration Branch', 5, 'Admin Branch', 5);
insert into `loosebox_pdm`.unit VALUES(11, 'Standard Evaluation Branch', 5, 'Stan Eval Branch', 5);
insert into `loosebox_pdm`.unit VALUES(12, 'Medical Services Branch', 5, 'Med Svcs Branch', 5);
insert into `loosebox_pdm`.unit VALUES(13, 'Air Secretary Branch', 5, 'Air Sec Branch', 5);
insert into `loosebox_pdm`.unit VALUES(14, 'Accounts and Budget Branch', 5, 'A & B Branch', 5);
insert into `loosebox_pdm`.unit VALUES(15, 'Special Instructions List', 14, 'SI List', 5);

drop table if exists `loosebox_pdm`.appointmenttype;
create table `loosebox_pdm`.appointmenttype (
    appointmenttypeid SMALLINT(2) NOT NULL PRIMARY KEY,
    appointmenttype VARCHAR(100) NOT NULL,
    abbreviation VARCHAR(50) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.appointmenttype VALUES(1, 'Defence Chief', 'Defence Chief');
insert into `loosebox_pdm`.appointmenttype VALUES(2, 'Service Chief', 'Svc Chief');
insert into `loosebox_pdm`.appointmenttype VALUES(3, 'Tri-Service', 'Tri-Svc');
insert into `loosebox_pdm`.appointmenttype VALUES(4, 'Branch Chief', 'Branch Chief');
insert into `loosebox_pdm`.appointmenttype VALUES(5, 'Air Officer Commanding', 'AOC');
insert into `loosebox_pdm`.appointmenttype VALUES(6, 'Commandant', 'Comdt');
insert into `loosebox_pdm`.appointmenttype VALUES(7, 'Deputy Commandant', 'Dy Comdt');
insert into `loosebox_pdm`.appointmenttype VALUES(8, 'Principal Air Staff Officer', 'PASO');
insert into `loosebox_pdm`.appointmenttype VALUES(9, 'Director', 'Dir');
insert into `loosebox_pdm`.appointmenttype VALUES(10, 'Commander', 'Comd');
insert into `loosebox_pdm`.appointmenttype VALUES(11, 'Deputy Director', 'Dy Dir');
insert into `loosebox_pdm`.appointmenttype VALUES(12, 'Command Specialist Officer', 'Comd Specialist Offr');
insert into `loosebox_pdm`.appointmenttype VALUES(13, 'Air Assistant', 'AA');
insert into `loosebox_pdm`.appointmenttype VALUES(14, 'Headqaurters Nigerian Air Force Staff Officer', 'HQ NAF Staff Offr');
insert into `loosebox_pdm`.appointmenttype VALUES(15, 'Command Staff Officer', 'Comd Staff Offr');
insert into `loosebox_pdm`.appointmenttype VALUES(16, 'Security Officer', 'Sy Offr');
insert into `loosebox_pdm`.appointmenttype VALUES(17, 'Commanding Officer', 'CO');
insert into `loosebox_pdm`.appointmenttype VALUES(18, 'Aide de Camp', 'ADC');
insert into `loosebox_pdm`.appointmenttype VALUES(19, 'Officer Commanding', 'OC');
insert into `loosebox_pdm`.appointmenttype VALUES(20, 'Flight Commander', 'Flt Comd');
insert into `loosebox_pdm`.appointmenttype VALUES(21, 'Admin Officer', 'AO');
insert into `loosebox_pdm`.appointmenttype VALUES(22, 'Other - Officer', 'Other - Offr');
insert into `loosebox_pdm`.appointmenttype VALUES(23, 'Air Warrant Officer', 'AWO');
insert into `loosebox_pdm`.appointmenttype VALUES(24, 'Regimental Seargent Major', 'RSM');
insert into `loosebox_pdm`.appointmenttype VALUES(25, 'Chief Clerk', 'CC');
insert into `loosebox_pdm`.appointmenttype VALUES(26, 'Supervisor', 'Supvr');
insert into `loosebox_pdm`.appointmenttype VALUES(27, 'Personal Assistant', 'PA');
insert into `loosebox_pdm`.appointmenttype VALUES(28, 'Driver', 'Dvr');
insert into `loosebox_pdm`.appointmenttype VALUES(29, 'Office Assistant', 'Office Asst');
insert into `loosebox_pdm`.appointmenttype VALUES(30, 'Orderly', 'Orderly');

drop table if exists `loosebox_pdm`.appointment;
create table `loosebox_pdm`.appointment
(
    appointmentid INTEGER(8) AUTO_INCREMENT not null primary key,
    appointment VARCHAR(100) not null UNIQUE,
    appointmenttype SMALLINT(2) not null,
    abbreviation VARCHAR(50) not null UNIQUE,
    parentappointment INTEGER(8) null,
    unit INTEGER(8) not null, 

    FOREIGN KEY (appointmenttype) REFERENCES `loosebox_pdm`.appointmenttype(appointmenttypeid) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (parentappointment) REFERENCES `loosebox_pdm`.appointment(appointmentid) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (unit) REFERENCES `loosebox_pdm`.unit(unitid) ON DELETE CASCADE ON UPDATE CASCADE

)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.appointment VALUES(1, 'Chief of Defence Staff', 1, 'CDS', null, 4);
insert into `loosebox_pdm`.appointment VALUES(2, 'Chief of the Air Staff', 2, 'CAS', 1, 5);
insert into `loosebox_pdm`.appointment VALUES(3, 'Chief of Policy & Plans', 4, 'COPP', 2, 6);
insert into `loosebox_pdm`.appointment VALUES(4, 'Chief of Training & Operations', 4, 'CTOP', 2, 7);
insert into `loosebox_pdm`.appointment VALUES(5, 'Chief of Aircraft Engineering', 4, 'CAcE', 2, 8);
insert into `loosebox_pdm`.appointment VALUES(6, 'Chief of Logistics & Communications', 4, 'CLog & Comms', 2, 9);
insert into `loosebox_pdm`.appointment VALUES(7, 'Chief of Administration', 4, 'COA', 2, 10);
insert into `loosebox_pdm`.appointment VALUES(8, 'Chief of Standard Evaluation', 4, 'COSE', 2, 11);
insert into `loosebox_pdm`.appointment VALUES(9, 'Chief of Medical Services', 4, 'CMS', 2, 12);
insert into `loosebox_pdm`.appointment VALUES(10, 'Air Secretary', 4, 'Air Sec', 2, 13);
insert into `loosebox_pdm`.appointment VALUES(11, 'Chief of Accounts and Budget', 4, 'CAB', 2, 14);
insert into `loosebox_pdm`.appointment VALUES(12, 'Principal Air Staff Officer to the Chief of Air Staff', 8, 'PASO-CAS', 2, 5);
insert into `loosebox_pdm`.appointment VALUES(13, 'Air Assistant to the Chief of Air Staff', 13, 'AA-CAS', 2, 5);
insert into `loosebox_pdm`.appointment VALUES(14, 'Director of Procurement', 9, 'DProc', 6, 9);

drop table if exists `loosebox_pdm`.commissiontype;
create table `loosebox_pdm`.commissiontype (
    commissiontypeid SMALLINT(2) NOT NULL PRIMARY KEY,
    commissiontype VARCHAR(100) NOT NULL,
    abbreviation VARCHAR(50) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.commissiontype VALUES(1, 'Regular Commission', 'RC');
insert into `loosebox_pdm`.commissiontype VALUES(2, 'Direct Regular Commission', 'DRC');
insert into `loosebox_pdm`.commissiontype VALUES(3, 'Direct Short Service Commission', 'DSSC');
insert into `loosebox_pdm`.commissiontype VALUES(4, 'Branch', 'Branch');

drop table if exists `loosebox_pdm`.speciality;
create table `loosebox_pdm`.speciality (
    specialityid SMALLINT(2) NOT NULL PRIMARY KEY,
    speciality VARCHAR(100) NOT NULL,
    abbreviation VARCHAR(50) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.speciality VALUES(1, 'Pilot', 'Plt');

drop table if exists `loosebox_pdm`.trade;
create table `loosebox_pdm`.trade (
    tradeid SMALLINT(2) NOT NULL PRIMARY KEY,
    trade VARCHAR(100) NOT NULL,
    abbreviation VARCHAR(50) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.trade VALUES(1, 'Load Master', 'Load Master');

drop table if exists `loosebox_pdm`.grade;
create table `loosebox_pdm`.grade (
    gradeid SMALLINT(2) NOT NULL PRIMARY KEY,
    grade VARCHAR(5) NOT NULL
)ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;
insert into `loosebox_pdm`.grade VALUES(1, 'X1');

drop table if exists `loosebox_pdm`.personneldata;
create table `loosebox_pdm`.personneldata
(
    personneldataid INTEGER(8) AUTO_INCREMENT not null primary key,
    servicenumber VARCHAR(24) not null UNIQUE,
    rank SMALLINT(2) not null,
    firstname VARCHAR(100) not null,
    middlename VARCHAR(100) not null,
    surname VARCHAR(100) not null,
    appointment INTEGER(8) null,
    seniority DATE not null,
    dateofbirth DATE not null,
    gender SMALLINT(2) not null,
    stateoforigin SMALLINT(2) not null,
    localgovernmentarea VARCHAR(100) not null,

    FOREIGN KEY (rank) REFERENCES `loosebox_pdm`.rank(rankid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (appointment) REFERENCES `loosebox_pdm`.appointment(appointmentid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (gender) REFERENCES `loosebox_pdm`.gender(genderid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (stateoforigin) REFERENCES `loosebox_pdm`.stateoforigin(stateoforiginid) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists `loosebox_pdm`.officersdata;
create table `loosebox_pdm`.officersdata
(
    officersdataid INTEGER(8) AUTO_INCREMENT not null primary key,
    personneldata INTEGER(8) not null UNIQUE,
    course VARCHAR(20) not null,
    dateofcommission DATE not null,
    commissiontype SMALLINT(2) not null,
    speciality SMALLINT(2) not null,

    FOREIGN KEY (personneldata) REFERENCES `loosebox_pdm`.personneldata(personneldataid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (commissiontype) REFERENCES `loosebox_pdm`.commissiontype(commissiontypeid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (speciality) REFERENCES `loosebox_pdm`.speciality(specialityid) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists `loosebox_pdm`.airmansdata;
create table `loosebox_pdm`.airmansdata
(
    airmansdataid INTEGER(8) AUTO_INCREMENT not null primary key,
    personneldata INTEGER(8) not null UNIQUE,
    dateofenlistment DATE not null,
    trade SMALLINT(2) not null,
    grade SMALLINT(2) not null,

    FOREIGN KEY (personneldata) REFERENCES `loosebox_pdm`.personneldata(personneldataid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (trade) REFERENCES `loosebox_pdm`.trade(tradeid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (grade) REFERENCES `loosebox_pdm`.grade(gradeid) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists `loosebox_pdm`.officersposting;
create table `loosebox_pdm`.officersposting
(
    officerspostingid INTEGER(8) AUTO_INCREMENT not null primary key,
    officersdata INTEGER(8) not null,
    unit INTEGER(8) not null,
    appointment INTEGER(8) null,
    datetakenonstrength DATE null, 

    FOREIGN KEY (officersdata) REFERENCES `loosebox_pdm`.officersdata(officersdataid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (unit) REFERENCES `loosebox_pdm`.unit(unitid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (appointment) REFERENCES `loosebox_pdm`.appointment(appointmentid) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table if exists `loosebox_pdm`.airmansposting;
create table `loosebox_pdm`.airmansposting
(
    airmanspostingid INTEGER(8) AUTO_INCREMENT not null primary key,
    airmansdata INTEGER(8) not null,
    unit INTEGER(8) not null,
    appointment INTEGER(8) null,
    datetakenonstrength DATE null, 

    FOREIGN KEY (airmansdata) REFERENCES `loosebox_pdm`.airmansdata(airmansdataid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (unit) REFERENCES `loosebox_pdm`.unit(unitid) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (appointment) REFERENCES `loosebox_pdm`.appointment(appointmentid) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

