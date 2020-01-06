/**
 * Created by xuhang on 2019/11/10.
 */
;
define(function (require, exports, module) {
    "require:nomunge,exports:nomunge,module:nomunge";

    /**
     * @class List
     * @constructor
     */
    function Comments() {
    }


    var paginator = require('bootstrap-paginator');

    /**
     * method to init page
     */
    Comments.prototype.init = function (pageNo, pageSize) {
        var self = this;
        self.paginator(pageNo, pageSize);
    }


    /**
     * method to init paginator
     */
    Comments.prototype.paginator = function (pageNo, pageSize) {
        var self = this;
        var options = {
            bootstrapMajorVersion: 3,
            currentPage: pageNo,
            totalPages: pageSize,
            itemTexts: function (type, page, current) {
                switch (type) {
                    case "first":
                        return "First";
                    case "prev":
                        return "Previous";
                    case "next":
                        return "Next";
                    case "last":
                        return "Last";
                    case "page":
                        return page;
                }
            },
            pageUrl: function (type, page, current) {
                return window.location.pathname + "?pageNo=" + page;

            }
        };
        $('#paginator').bootstrapPaginator(options);

    };


    module.exports = new Comments();
})
;