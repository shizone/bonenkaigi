var viewModel = {
  idx: ko.observable(0),
  member: ko.observable("")
};

function paging(idx) {
  if (members != null && members.member != null && 0 <= idx && idx < members.member
    .length) {
    viewModel.idx(idx);
    viewModel.member(members.member[viewModel.idx()].text);
  }
}

function prev() {
  paging(viewModel.idx() - 1);
}

function next() {
  paging(viewModel.idx() + 1);
}

function first() {
  paging(0);
}

function last() {
  if (members != null && members.member != null) {
    paging(members.member.length - 1);
  }
}

var members = null;
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
    if (this.response) {
      members = this.response;
      console.log(members);
      paging(0);
      ko.applyBindings(viewModel);
    }
  }
};
xmlHttpRequest.open('POST', 'members', true);
xmlHttpRequest.responseType = 'json';
xmlHttpRequest.send(null);

document.onkeydown = function(e) {
  if (e.keyCode == 37) {
    prev();
  } else if (e.keyCode == 39) {
    next();
  }
};
