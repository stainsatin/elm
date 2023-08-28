window.onload = function(){
	//获取显示隐藏按钮对象
	let downBtn  = document.getElementById('downBtn');
	//获取隐藏订单明细对象
	let detailBox = document.getElementById('detailBox');
	//初始为隐藏状态
	detailBox.style.display='none';
	downBtn.onclick = function(){
		//判断隐藏订单明细是否隐藏
		if(detailBox.style.display=='none'){
			detailBox.style.display='block';
		}
		else{
			detailBox.style.display='none';
		}
	}
}