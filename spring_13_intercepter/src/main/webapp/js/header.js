function init(chk){
/*var chk = '${empty sessionScope.chk}' == 'true' ? true : false;*/

		if (chk) {
			$('#logInPage').removeClass('hidePage').addClass('showPage');
			$('#logOutPage').removeClass('showPage').addClass('hidePage');
		} else {
			$('#logInPage').removeClass('showPage').addClass('hidePage');
			$('#logOutPage').removeClass('hidePage').addClass('showPage');
		}
}