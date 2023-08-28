window.onload = function(){
	//获取显示隐藏按钮对象
	let downBtn  = document.getElementsByClassName('fa-caret-down');
	//获取隐藏订单明细对象
	let detailBox = document.getElementsByClassName('order-detail');
	//所有订单明细初始为隐藏状态
	for(let i = 0; i < downBtn.length; i++){
		detailBox[i].style.display='none';
	}
	for(let i = 0; i < downBtn.length; i++){
		downBtn[i].onclick = function(){
			//判断隐藏订单明细是否隐藏
			if(detailBox[i].style.display=='none'){
				detailBox[i].style.display='block';
			}
			else{
				detailBox[i].style.display='none';
			}
		}
	}
	
}