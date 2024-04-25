/**
 * 1. 使用 onscroll，移动端和PC端都有效
 * 2. 获取PC端滚动条位置：document.documentElement.scrollTop
 * 	  获取移动端滚动条位置：document.body.scrollTop
 * 3. 获取视口宽度： document.documentElement.clientWidth 移动端PC端都有效
 */
window.onload = function(){
	document.onscroll = function(){
		//获取滚动条位置
		let s1 = document.documentElement.scrollTop;
		let s2 = document.body.scrollTop;
		let scroll = s1==0?s2:s1; //判断是否是移动端或PC端
		//获取视口宽度
		let width = document.documentElement.clientWidth;
		//获取固定块
		let search = document.getElementById('fixedBox');
		if(scroll > width * 0.12){ //因为header的高度是 12vw
			search.style.position = 'fixed';
			search.style.left = '0';
			search.style.top = '0';
		}
		else{
			search.style.position = 'static';
		}
	}
}