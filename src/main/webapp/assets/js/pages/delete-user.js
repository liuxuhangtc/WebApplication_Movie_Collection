/**
 * Created by xuhang on 2019/11/10.
 */
;
define(function (require, exports, module) {
    "require:nomunge,exports:nomunge,module:nomunge";

    /**
     * @class DeleteComment
     * @constructor
     */
    function DeleteComment() {
        this.init();
        console.log('DeleteComment init calling');
    }


    var tool = require("ui-helper.js");

    /**
     * method to init page
     */
    DeleteComment.prototype.init = function () {

        $(".delete_comment").bind("click", function (e) {
            var jqCtrl = $(this);
            $.post("/user/delete", {id: jqCtrl.val()}, function (data) {
                if (!data.success) {
                    $(this).focus();
                    tool.tooltip(jqCtrl, data.comment, null, true);
                    return;
                } else {
                    window.location.reload();
                }
            }, "json");
        });
    }


    module.exports = new DeleteComment();
})
;