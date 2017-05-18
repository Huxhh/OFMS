var app = angular.module('app', ['ui.router']);
app.factory('request', ['$http', function ($http){
	var r = {};
	var count = 0;
	var pop;
	var htm;
	//弹窗
	r.pop_up = function (message) {
		count ++;
		htm = `<div class="popup">
					<div class="bg bg_pop"></div>
					<div>
						<span>` + count + `</span>
						<p>` + message + `</p>
						<input type="button" value="确定" />
					</div>
				</div>`
		pop = angular.element(htm);
		angular.element(document.getElementsByTagName('body')).append(pop);
		pop.find('input').on('click', function () {
			count --;
			pop.remove('div.popup')
		})
	}
	r.remove_pop = function () {
		pop = document.getElementsByTagName('body')[0].getElementsByTagName('div');
		for (var i = 0; i < pop.length; ++i) {
			if (pop[i].getAttribute('class') == 'popup') {
				count --;
				document.getElementsByTagName('body')[0].removeChild(pop[i]);
			}
		}
	}
	//确认
	r.comfirm = function (callback, param) {
		htm = `<div class="popup">
					<div class="bg bg_pop"></div>
					<div class="comfirm">
						<input type="button" value="确定" />
						<input type="button" value="取消" class="cancle"/>
					</div>
				</div>`
		pop = angular.element(htm);
		angular.element(document.getElementsByTagName('body')).append(pop);
		pop.find('input').eq(1).on('click', function () {
			pop.remove('div.popup')
		})
		pop.find('input').eq(0).on('click', function () {
			pop.remove('div.popup');
			callback.apply(null, param);
		})
	}
	//get请求
	r.get = function (url, callback1, callback2) {
		$http({
			url: url,
			method: 'GET'
		}).then(function (res) {
			callback1(res.data)
		}, function () {
			if (callback2) {
				callback2();
			}
			r.pop_up(url + ' get failed');
		})
	}
	//post请求
	r.post = function (url, data, callback1, callback2) {
		$http({
			url: url,
			data: data,
			method: 'POST'
		}).then(function (res) {
			callback1(res.data)
		}, function () {
			if (callback2) {
				callback2();
			}
			r.pop_up(url + ' post failed');
		})
	}
	//put请求
	r.put = function (url, data, callback1, callback2) {
		$http({
			url: url,
			data: data,
			method: 'PUT'
		}).then(function (res) {
			callback1(res.data)
		}, function () {
			if (callback2) {
				callback2();
			}
			r.pop_up(url + ' put failed');
		})
	}
	//delete请求
	r.delete = function (url, data, callback1, callback2) {
		$http({
			url: url,
			data: data,
			method: 'delete'
		}).then(function (res) {
			callback1(res.data)
		}, function () {
			if (callback2) {
				callback2();
			}
			r.pop_up(url + ' delete failed');
		})
	}
	// r.make_page()
	r.resize = function () {
		// var clientHeight, scrollHeight;
		// var body = angular.element(document.getElementsByTagName('body'));
		// if ((clientHeight = top.document.body.clientHeight) < (scrollHeight = document.body.scrollHeight)) {
		// 	body.css({'height': scrollHeight + 'px'})
		// } else {
		// 	body.css({'height': clientHeight + 'px'})
		// }
		// console.log(clientHeight, scrollHeight)
	}
	return r;
}]);
app.controller('login', ['$scope', function ($scope) {
	$scope.user = {
		UserPswd: null,
		UserName: null
	}
}]);
app.directive('loginDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function () {
			scope.login = function () {
				if (scope.user.UserName && scope.user.UserPswd) {
					request.post('/admin/login', {
						userName: scope.user.UserName,
						password: scope.user.UserPswd
					}, function (res) {
						if (res.code == 0) {
							location.pathname = '/html/admin/index.html'
						} else {
							request.popup(res.msg);
						}
					})
				}
			}
		}
	}
}]);