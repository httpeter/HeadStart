CREATE TABLE `example`.`FOOD` (
  `ID` INT(11) NOT NULL,
  `NAME` VARCHAR(255) NULL,
  `CALORIES` INT(11) NULL,
  PRIMARY KEY (`ID`));


INSERT INTO `example`.`FOOD` (`ID`, `NAME`, `CALORIES`) VALUES ('1', 'Broccoli', '100');
INSERT INTO `example`.`FOOD` (`ID`, `NAME`, `CALORIES`) VALUES ('2', 'Potatoes', '150');
INSERT INTO `example`.`FOOD` (`ID`, `NAME`, `CALORIES`) VALUES ('3', 'Bread', '133');
