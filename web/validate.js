function adminClick() {
	var admin = document.getElementById("admin");
	admin.style.display = "block";
	var studentlogin = document.getElementById("studentlogin");
	var studentregister = document.getElementById("studentregister");
	studentlogin.style.display = "none";
	studentregister.style.display = "none";
}
function studentloginClick() {
	var admin = document.getElementById("admin");
	var studentlogin = document.getElementById("studentlogin");
	var studentregister = document.getElementById("studentregister");
	studentlogin.style.display = "block";
	studentregister.style.display = "none";
	admin.style.display = "none";
}
function studentregisterClick() {
	var admin = document.getElementById("admin");
	var studentlogin = document.getElementById("studentlogin");
	var studentregister = document.getElementById("studentregister");
	studentlogin.style.display = "none";
	studentregister.style.display = "block";
	admin.style.display = "none";
}
function aClick() {
	var add = document.getElementById("addstudent");
	var removestudent = document.getElementById("removestudent");
	var addcourse = document.getElementById("addcourse");
	var removecourse = document.getElementById("removecourse");
	removestudent.style.display = "none";
	add.style.display = "block";
	addcourse.style.display = "none";
	removecourse.style.display = "none";
	add.style.display = "block";
}
function bClick() {
	var add = document.getElementById("addstudent");
	var removestudent = document.getElementById("removestudent");
	var addcourse = document.getElementById("addcourse");
	var removecourse = document.getElementById("removecourse");
	add.style.display = "none";
	removestudent.style.display = "block";
	addcourse.style.display = "none";
	removecourse.style.display = "none";
}
function cClick() {
	var add = document.getElementById("addstudent");
	var removestudent = document.getElementById("removestudent");
	var addcourse = document.getElementById("addcourse");
	var removecourse = document.getElementById("removecourse");
	addcourse.style.display = "block";
	add.style.display = "none";
	removestudent.style.display = "none";
	removecourse.style.display = "none";

}
function dClick() {
	var add = document.getElementById("addstudent");
	var removestudent = document.getElementById("removestudent");
	var addcourse = document.getElementById("addcourse");
	var removecourse = document.getElementById("removecourse");
	addcourse.style.display = "none";
	removecourse.style.display = "block";
	add.style.display = "none";
	removestudent.style.display = "none";
}


function enrollCourseClick() {
	var enrollCousre = document.getElementById("enrollcousre");
	var courseDetails = document.getElementById("coursedetails");
	enrollCousre.style.display = "block";
	courseDetails.style.display = "none";
}
function coursedetailsClick() {
	var enrollCousre = document.getElementById("enrollcousre");
	var courseDetails = document.getElementById("coursedetails");
	courseDetails.style.display = "block";
	enrollCousre.style.display = "none";
}
function validateAddStudent() {
	var sid = document.forms["addstudent"]["sid"].value;
	var sname = document.forms["addstudent"]["sname"].value;
	var ssem = document.forms["addstudent"]["ssem"].value;
	var regX = /^[0-9]+$/;
	var regX1 = /^[a-zA-Z ]+$/;
	if (!regX.test(sid)) {
		alert("Id Field Should Contain Only Number");
		return false;
	}
	if (!regX.test(ssem)) {
		alert("Semester Field Should Contain Only Number");
		return false;
	}
	if (!regX1.test(sname)) {
		alert("Name Should Not Contain Special Character");
		return false;
	}
	return true;
}
function validateRemoveStudent() {
	var sid = document.forms["removestudent"]["deletesid"].value;
	var regX = /^[0-9]+$/;
	if (!regX.test(sid)) {
		alert("Id Field Should Contain Only Number");
		return false;
	}
	return true;
}
function validateAddCourse() {
	var courseId = document.forms["addcourse"]["addcourse_id"].value;
	var courseName = document.forms["addcourse"]["addcourse_name"].value;
	var regX = /^[a-zA-Z ]+$/;
	var regX1 = /^[a-zA-Z0-9]+$/;
	if (!regX.test(courseName)) {
		alert("Course Name Should Not Contain Special Character and Number");
		return false;
	}
	if (!regX1.test(courseId)) {
		alert("Course Id Should Not Contain Special Character");
		return false;
	}
	return true;
}
function validateRemoveCourse() {
	var courseId = document.forms["removecourse"]["deletecourse"].value;
	var regX1 = /^[a-zA-Z0-9]+$/;
	if (!regX1.test(courseId)) {
		alert("Please Enter Valid Course Id");
		return false;
	}
	return true;
}
function validateStudentRegister() {
	var sid = document.forms["studentregister"]["s_id"].value;
	var sname = document.forms["studentregister"]["s_name"].value;
	var ssem = document.forms["studentregister"]["s_sem"].value;
	var regX = /^[0-9]+$/;
	var regX1 = /^[a-zA-Z ]+$/;
	if (!regX.test(sid)) {
		alert("Id Field Should Contain Only Number");
		return false;
	}
	if (!regX.test(ssem)) {
		alert("Semester Field Should Contain Only Number");
		return false;
	}
	if (!regX1.test(sname)) {
		alert("Name Should Not Contain Special Character");
		return false;
	}
	return true;
}

function validateStudentLogin() {
	var sid = document.forms["studentlogin"]["l_s_id"].value;
	var sname = document.forms["studentlogin"]["l_s_name"].value;
	var regX = /^[0-9]+$/;
	var regX1 = /^[a-zA-Z ]+$/;
	if (!regX.test(sid)) {
		alert("Id Field Should Contain Only Number");
		return false;
	}
	if (!regX1.test(sname)) {
		alert("Name Should Not Contain Special Character");
		return false;
	}
	return true;
}
function validateEnrollCourse() {
	var courseId = document.forms["enrollcousre"]["enrollcourseId"].value;
	var regX1 = /^[a-zA-Z0-9]+$/;
	if (!regX1.test(courseId)) {
		alert("Please Enter Valid Course Id");
		return false;
	}
	return true;
}
function validateCourseDetails() {
	var courseId = document.forms["coursedetails"]["coursedetailsSubmit"].value;
	var regX1 = /^[a-zA-Z0-9]+$/;
	if (!regX1.test(courseId)) {
		alert("Please Enter Valid Course Id");
		return false;
	}
	return true;
}
setTimeout(() => {
	const e = document.getElementById('delete');
	e.style.display = 'none';
}, 10000);
setTimeout(() => {
	const e = document.getElementById('enroll');
	e.style.display = 'none';
}, 10000);

function prevent() {
	console.log(document.referrer);
	if (document.referrer == '') self.location = 'index.html';
}


function loadDoc() {
    
  var  xhttp = new XMLHttpRequest();
  xhttp.open("GET", "AdministratorServlet",true);
  xhttp.send();
    alert(msg);
  }
  
  var xMLHtttpResquest = new XMLHttpResquest();
  function findTurotial(){
      xMLHtttpResquest.open("Get","Myservlet",true);
      xMLHtttpResquest.onreadystatechange=processreadystate;
      xMLHtttpResquest.send(null);
  }
  function processreadystate(){
      if(xMLHtttpResquest.readyState==4 && xMLHtttpResquest.status==200){
          var e=xMLHtttpResquest.responseText;
          alert(e);
      }
  }