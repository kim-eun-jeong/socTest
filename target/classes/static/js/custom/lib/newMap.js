if (typeof (Utils) == "undefined") var Utils = {};


(function($) {
	
	/* HashMap 객체 생성 */
	Utils.NewMap = function() {
		this.map = new Object();
	};
	

	Utils.NewMap.prototype = {
		/* key, value 값으로 구성된 데이터를 추가 */
		set: function (key, value) {
			this.map[key] = value;
		},
		
		/* 지정한 key값의 value값 반환 */
		get: function (key) {
			return this.map[key];
		},
		
		/* 구성된 key 값 존재여부 반환 */
		hasKey: function (key) {
			return key in this.map;
		},
		
		/* 구성된 value 값 존재여부 반환 */
		hasValue: function (value) {
			for (var prop in this.map) {
				if (this.map[prop] == value) {
					return true;
				}
			}
			return false;
		},

		/* 구성된 데이터 초기화 */
		clear: function () {
			for (var prop in this.map) {
				delete this.map[prop];
			}
		},
		
		/*  key에 해당하는 데이터 삭제 */
		remove: function (key) {
			delete this.map[key];
		},

		/* 배열로 key 반환 */
		keys: function () {
			var arKey = new Array();
			for (var prop in this.map) {
				arKey.push(prop);
			}
			return arKey;
		},

		/* 배열로 value 반환 */
		values: function () {
			var arVal = new Array();
			for (var prop in this.map) {
				arVal.push(this.map[prop]);
			}
			return arVal;
		},
		
		every : function(f, opt_obj) {
			for (var key in this.map) {
				f.call(opt_obj, this.map[key], key);
			}
		},
		
		
		/* Map에 구성된 개수 반환 */
		size: function () {
			var count = 0;
			for (var prop in this.map) {
				count++;
			}
			return count;
		}
			
		
	};
	
})(jQuery);


var NewMap = function(){
	return new Utils.NewMap();
}
