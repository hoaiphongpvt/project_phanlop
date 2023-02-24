-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th2 24, 2023 lúc 03:11 PM
-- Phiên bản máy phục vụ: 10.4.18-MariaDB
-- Phiên bản PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `school`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `collegefee`
--

CREATE TABLE `collegefee` (
  `rollno` varchar(20) NOT NULL,
  `course` varchar(20) NOT NULL,
  `branch` varchar(20) NOT NULL,
  `semester` varchar(20) NOT NULL,
  `total` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `course`
--

CREATE TABLE `course` (
  `CourseID` int(11) NOT NULL,
  `Title` varchar(100) NOT NULL,
  `Credits` int(11) NOT NULL,
  `DepartmentID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `course`
--

INSERT INTO `course` (`CourseID`, `Title`, `Credits`, `DepartmentID`) VALUES
(1045, 'Calculus', 4, 7),
(1050, 'Chemistry', 4, 1),
(1061, 'Physics', 4, 1),
(2021, 'Composition', 3, 2),
(2030, 'Poetry', 2, 2),
(2042, 'Literature', 4, 2),
(3141, 'Trigonometry', 4, 7),
(4022, 'Microeconomics', 3, 4),
(4041, 'Macroeconomics', 3, 4),
(4061, 'Quantitative', 2, 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `courseinstructor`
--

CREATE TABLE `courseinstructor` (
  `CourseID` int(11) NOT NULL,
  `PersonID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `courseinstructor`
--

INSERT INTO `courseinstructor` (`CourseID`, `PersonID`) VALUES
(1050, 967);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `department`
--

CREATE TABLE `department` (
  `DepartmentID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Budget` double NOT NULL,
  `StartDate` datetime NOT NULL,
  `Administrator` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `department`
--

INSERT INTO `department` (`DepartmentID`, `Name`, `Budget`, `StartDate`, `Administrator`) VALUES
(1, 'Engineering', 350000, '2007-09-01 00:00:00', 2),
(2, 'English', 120000, '2007-09-01 00:00:00', 6),
(4, 'Economics', 200000, '2007-09-01 00:00:00', 4),
(7, 'Mathematics', 250000, '2007-09-01 00:00:00', 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `fee`
--

CREATE TABLE `fee` (
  `course` varchar(20) NOT NULL,
  `semester1` varchar(20) NOT NULL,
  `semester2` varchar(20) NOT NULL,
  `semester3` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `fee`
--

INSERT INTO `fee` (`course`, `semester1`, `semester2`, `semester3`) VALUES
('BCA', '48000', '49000', '50000'),
('BTech', '48000', '45000', '49000'),
('HT', '48000', '45000', '49000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `login`
--

CREATE TABLE `login` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('admin', '123'),
('', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `officeassignment`
--

CREATE TABLE `officeassignment` (
  `InstructorID` int(11) NOT NULL,
  `Location` varchar(50) NOT NULL DEFAULT 'TP.HCM',
  `Timestamp` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `officeassignment`
--

INSERT INTO `officeassignment` (`InstructorID`, `Location`, `Timestamp`) VALUES
(11024, 'TPHCM', '9 thg 2, 2023'),
(11202, 'TPHCM', '9 thg 2, 2023'),
(11234, '213 Smith', '2022-02-24 00:19:22'),
(11243, '203 Williams', '2022-02-24 00:19:22'),
(11252, '29 Adams', '2022-02-24 00:19:21'),
(11352, '37 Williams', '2022-02-24 00:19:22'),
(11437, '271 Williams', '2022-02-24 00:19:22'),
(11542, '57 Adams', '2022-02-24 00:19:22'),
(11690, 'VN', '15 thg 2, 2023'),
(11814, 'TPHCM Q1', '20 thg 2, 2023'),
(11845, '143 Smith', '2022-02-24 00:19:22');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `onlinecourse`
--

CREATE TABLE `onlinecourse` (
  `CourseID` int(11) NOT NULL,
  `url` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `onlinecourse`
--

INSERT INTO `onlinecourse` (`CourseID`, `url`) VALUES
(2021, 'http://www.fineartschool.net/Composition'),
(2030, 'http://www.fineartschool.net/Poetry'),
(3141, 'http://www.fineartschool.net/Trigonometry'),
(4041, 'http://www.fineartschool.net/Macroeconomics');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `onsitecourse`
--

CREATE TABLE `onsitecourse` (
  `CourseID` int(11) NOT NULL,
  `Location` varchar(50) NOT NULL,
  `Days` varchar(50) NOT NULL,
  `Time` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `onsitecourse`
--

INSERT INTO `onsitecourse` (`CourseID`, `Location`, `Days`, `Time`) VALUES
(1045, 'HCM', 'T2,T4,T6', '1:00:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `person`
--

CREATE TABLE `person` (
  `PersonID` bigint(20) NOT NULL,
  `Lastname` varchar(50) NOT NULL,
  `Firstname` varchar(50) NOT NULL,
  `HireDate` varchar(50) DEFAULT NULL,
  `EnrollmentDate` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `person`
--

INSERT INTO `person` (`PersonID`, `Lastname`, `Firstname`, `HireDate`, `EnrollmentDate`) VALUES
(3120410001, 'hhi', 'hi', '11 thg 2, 2023', '8 thg 2, 2023'),
(3120410002, 'Barzdukas', 'Gytis', NULL, '8 thg 2, 2002'),
(3120410003, 'Justice', 'Peggy', NULL, '8 thg 2, 1999'),
(3120410004, 'hi', 'Fadi', '8 thg 2, 2023', NULL),
(3120410005, 'Harui', 'Roger', '8 thg 2, 2023', NULL),
(3120410006, 'Li', 'Yan', NULL, '8 thg 2, 2011'),
(3120410007, 'Norman', 'Laura', NULL, NULL),
(3120410008, 'Olivotto', 'Nino', NULL, NULL),
(3120410009, 'Tang', 'Wayne', NULL, NULL),
(3120410010, 'Alonso', 'Meredith', NULL, NULL),
(3120410011, 'Lopez', 'Sophia', NULL, NULL),
(3120410012, 'Browning', 'Meredith', NULL, NULL),
(3120410013, 'Anand', 'Arturo', NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `studentgrade`
--

CREATE TABLE `studentgrade` (
  `EnrollmentID` int(11) NOT NULL,
  `CourseID` int(11) NOT NULL,
  `StudentID` bigint(20) NOT NULL,
  `Grade` decimal(3,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `studentgrade`
--

INSERT INTO `studentgrade` (`EnrollmentID`, `CourseID`, `StudentID`, `Grade`) VALUES
(1123, 1045, 3120410005, '4.00'),
(1524, 1045, 3120410002, '4.00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `studentleave`
--

CREATE TABLE `studentleave` (
  `rollno` varchar(20) NOT NULL,
  `date` varchar(50) NOT NULL,
  `duration` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `studentleave`
--

INSERT INTO `studentleave` (`rollno`, `date`, `duration`) VALUES
('312041007', '17 thg 2, 2023', 'Full Day'),
('312041004', '9 thg 2, 2023', 'Full Day'),
('312041352', '16 thg 2, 2023', 'Full Day');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `teacher`
--

CREATE TABLE `teacher` (
  `empId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `courseID` int(11) NOT NULL,
  `department` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `teacher`
--

INSERT INTO `teacher` (`empId`, `name`, `fname`, `courseID`, `department`) VALUES
(306, 'mt', 'mt', 1050, 7),
(967, '', '', 1045, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `teacherleave`
--

CREATE TABLE `teacherleave` (
  `empId` varchar(20) NOT NULL,
  `date` varchar(50) NOT NULL,
  `duration` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `collegefee`
--
ALTER TABLE `collegefee`
  ADD PRIMARY KEY (`rollno`),
  ADD KEY `fk_course2` (`course`);

--
-- Chỉ mục cho bảng `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`CourseID`),
  ADD KEY `fk_department` (`DepartmentID`);

--
-- Chỉ mục cho bảng `courseinstructor`
--
ALTER TABLE `courseinstructor`
  ADD PRIMARY KEY (`CourseID`,`PersonID`),
  ADD KEY `fk_person` (`PersonID`);

--
-- Chỉ mục cho bảng `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`DepartmentID`);

--
-- Chỉ mục cho bảng `fee`
--
ALTER TABLE `fee`
  ADD PRIMARY KEY (`course`);

--
-- Chỉ mục cho bảng `officeassignment`
--
ALTER TABLE `officeassignment`
  ADD PRIMARY KEY (`InstructorID`);

--
-- Chỉ mục cho bảng `onlinecourse`
--
ALTER TABLE `onlinecourse`
  ADD PRIMARY KEY (`CourseID`);

--
-- Chỉ mục cho bảng `onsitecourse`
--
ALTER TABLE `onsitecourse`
  ADD PRIMARY KEY (`CourseID`);

--
-- Chỉ mục cho bảng `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`PersonID`);

--
-- Chỉ mục cho bảng `studentgrade`
--
ALTER TABLE `studentgrade`
  ADD PRIMARY KEY (`EnrollmentID`),
  ADD KEY `fk_student` (`StudentID`),
  ADD KEY `fk_course3` (`CourseID`);

--
-- Chỉ mục cho bảng `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`empId`),
  ADD KEY `fk_course1` (`courseID`),
  ADD KEY `fk_department1` (`department`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `studentgrade`
--
ALTER TABLE `studentgrade`
  MODIFY `EnrollmentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19716;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `collegefee`
--
ALTER TABLE `collegefee`
  ADD CONSTRAINT `fk_course2` FOREIGN KEY (`course`) REFERENCES `fee` (`course`);

--
-- Các ràng buộc cho bảng `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `fk_department` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`DepartmentID`);

--
-- Các ràng buộc cho bảng `courseinstructor`
--
ALTER TABLE `courseinstructor`
  ADD CONSTRAINT `fk_course` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`),
  ADD CONSTRAINT `fk_person` FOREIGN KEY (`PersonID`) REFERENCES `teacher` (`empId`);

--
-- Các ràng buộc cho bảng `onlinecourse`
--
ALTER TABLE `onlinecourse`
  ADD CONSTRAINT `onlinecourse_ibfk_1` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`);

--
-- Các ràng buộc cho bảng `studentgrade`
--
ALTER TABLE `studentgrade`
  ADD CONSTRAINT `fk_course3` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`),
  ADD CONSTRAINT `fk_student` FOREIGN KEY (`StudentID`) REFERENCES `person` (`PersonID`);

--
-- Các ràng buộc cho bảng `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `fk_course1` FOREIGN KEY (`courseID`) REFERENCES `course` (`CourseID`),
  ADD CONSTRAINT `fk_department1` FOREIGN KEY (`department`) REFERENCES `department` (`DepartmentID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
