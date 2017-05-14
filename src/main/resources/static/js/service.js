app.factory('request', ['$http', function ($http){
	var r = {};
	var count = 0;
	//弹窗
	r.pop_up = function (message) {
		count ++;
		var htm = `<div class="popup">
					<div class="bg bg_pop"></div>
					<div>
						<span>` + count + `</span>
						<p>` + message + `</p>
						<input type="button" value="确定" />
					</div>
				</div>`
		var pop = angular.element(htm);
		angular.element(document.getElementsByTagName('body')).append(pop);
		pop.find('input').on('click', function () {
			count --;
			pop.remove('div.popup')
		})
	}
	//确认
	r.comfirm = function (callback, param) {
		var htm = `<div class="popup">
					<div class="bg bg_pop"></div>
					<div class="comfirm">
						<input type="button" value="确定" />
						<input type="button" value="取消" class="cancle"/>
					</div>
				</div>`
		var pop = angular.element(htm);
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
	return r;
}]);