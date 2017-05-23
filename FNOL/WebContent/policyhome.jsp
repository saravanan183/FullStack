
<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<style>
table.fields {
	margin: 0 auto !important;
}

td.fieldTitleRequiredStatic {
	padding-left: 20px;
	padding-right: 15px;
	width: 200px;
	font-size: 12px;
	text-align: right;
	font-weight: bold;
}

div#wrapper {
	width: 770px;
	margin: 0 auto;
	text-align: center;
	background-color: #fff;
	border: 1px solid #ccc;
	padding: 0;
}

td.section {
	background-color: #607193;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: normal;
	font-size: 13px;
	color: #ffffff;
	padding: 4 4 4 12;
}

table.sect {
	width: 700px;
	margin-bottom: 18px;
}

div {
	background-color: white;
}

p {
	background-color: yellow;
}

.defaultTextBox {
	padding: 0;
	height: 30px;
	position: relative;
	left: 0;
	outline: none;
	border: 1px solid #cdcdcd;
	border-color: rgba(0, 0, 0, .15);
	background-color: white;
	font-size: 16px;
}

.header {
	background: red;
	height: 100px;
	left: 0;
	right: 0;
	top: 0px;
	margin-top: 100px;
	position: absolute;
}

.center {
	text-align: center;
}
</style>

<script>
	function save() {

		var x = document.getElementById("policynumber").value.trim();
		var submit = document.getElementById("submit");
		var n = x.length;

		if (x == "") {
			alert("No data entered");
			return false;
		}
		if (n != 14) {
			alert("Policy Number should be 14 characters");
			return false;
		}
		if (!x.match(/[A-Za-z]/i)) {
			alert("Policy Number Invalid");
			return false;
		}
		validateProperty();
		
	}

	function validateProperty() {
		if (document.getElementById("isPrimaryRes").value == "---") {
			alert("Please answer whether this is your Primary residence.")
			return false;
		}
		var val = document.getElementById("propDmgDesc").value;
		if (val == "" || val.length < 5) {
			alert("Please describe damaged property.")
			return false;
		}
		var amt = document.getElementById("estAmt").value;
		if (amt == "") {
			alert("Please enter Amount")
			return false;
		}
		window.location.href = "success.jsp"
	}
</script>
</head>
<body bgcolor="lightgrey">
	<form>

		<div id="wrapper">
			<div align="center">
				<div width="100%">
					<table bgcolor="white" width="100%">
						<tr>
							<td><img src="libertymutual.png"
								style="width: 190px; height: 70px;"></td>
							<td>
								<h1>First Notice of Loss</h1>
							</td>
						</tr>
					</table>
				</div>



				<table class="sect" id="PolicyHolder">
					<tbody>
						<tr>
							<td class="section">Policy Holder</td>
						</tr>
					</tbody>
				</table>

				<table class="fields" align="left">
					<tbody>
						<tr>
							<td class="fieldTitleRequiredStatic"><font color="red">*</font>Policy
								Number:</td>
							<td align="left"><input id="policynumber"
								name="policynumber" type="text" size="40" maxlength="14"
								value=""></td>
						</tr>
						<tr>
							<td class="fieldTitleRequiredStatic"><font color="red">*</font>Policy
								Holder Name:</td>
							<td align="left"><input name="name" type="text" size="40"
								value=""></td>

						</tr>
						<tr>
							<td class="fieldTitleRequiredStatic"><font color="red">*</font>Phone
								Number:</td>
							<td align="left"><input name="phone" type="text" size="40"
								value=""></td>

						</tr>
						<tr>
							<td class="fieldTitleRequiredStatic"><font color="red">*</font>Email
								Id:</td>
							<td align="left"><input name="email" type="text" size="40"
								value=""></td>
						</tr>
					</tbody>
				</table>
				<br />
				<table class="sect" id="Property Damaged">
					<tbody>
						<tr>
							<td class="section">Property Damaged</td>
						</tr>
					</tbody>
				</table>
				<table class="fields" align="left">
					<tbody>
						<tr>
							<td class="fieldTitleRequiredStatic"><font color="red">*</font>Is
								this loss your primary residence?</td>
							<td align="left"><select id="isPrimaryRes"
								name="isPrimaryRes">
									<option value="---"></option>
									<option value="No">No</option>
									<option value="Yes">Yes</option>
							</select></td>
						</tr>
						<tr>
							<td class="fieldTitleRequiredStatic"><font color="red">*</font>Describe
								damaged property</td>
							<td align="left"><input id="propDmgDesc" name="propDmgDesc"
								type="text" size="40" maxlength="120" value=""
								title="Please describe in 120 characters"></td>
						</tr>
						<tr>
							<td class="fieldTitleRequiredStatic">Is the property still
								inhabital/operating?</td>
							<td align="left"><select id="isInhabital" name="isInhabital">
									<option value="---"></option>
									<option value="No">No</option>
									<option value="Yes">Yes</option>
							</select></td>
						</tr>
						<tr>
							<td class="fieldTitleRequiredStatic"><font color="red">*</font>Estimated
								damage amount (In $)</td>
							<td align="left"><input id="estAmt" name="estAmt"
								type="number" pattern="^\d+(\.|\,)\d{2}$" size="40" value=""></td>
						</tr>
						<tr>
							<td class="fieldTitleRequiredStatic">Describe any related
								injuries (if applicable)</td>
							<td align="left"><input id="injuries" name="injuries"
								type="text" size="40" value=""></td>
						</tr>
					</tbody>
				</table>
				<br />
				<table class="sect" id="Submit">
					<tbody>
						<tr>
						<td align="center">
							<input type="button" value="Submit Request"
								onclick="javascript:save();"
								style="font-size: 7pt; color: white; background-color: #607193; border: 2px solid #336600; padding: 3px" />
						</td>
						</tr>
					</tbody>
				</table>				
			</div>
		</div>
	</form>
</body>
</html>