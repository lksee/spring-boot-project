const main = {
  // 초기화 함수
  init() {
    const btnSave = document.querySelector('#btn-save');
    if(btnSave) btnSave.addEventListener('click', () => this.save());

    const btnUpdate = document.querySelector('#btn-update');
    if(btnUpdate) btnUpdate.addEventListener('click', () => this.update());

    const btnDelete = document.querySelector('#btn-delete');
    if(btnDelete) btnDelete.addEventListener('click', () => this.delete());
  },
  // 데이터 저장 함수
  save() {
    const data = {
      title: document.querySelector('#title').value,
      author: document.querySelector('#author').value,
      content: document.querySelector('#content').value,
    };
    // fetch API를 이용해 POST 요청을 보내고 결과 처리
    fetch('/api/v1/posts', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json; charset=utf-8',
      },
      body: JSON.stringify(data),
    })
      .then((response) => {
        if (response.status === 200 || response.status === 201) {
        // 저장 성공 시 메시지 출력 후 메인 페이지로 이동
          alert('글이 등록되었습니다.');
          window.location.href = '/';
        } else {
        // 저장 실패 시 오류 메시지 출력
          alert('오류가 발생했습니다.');
        }
      })
      .catch((error) => {
        // 네트워크 오류 등 예외 발생 시 에러 메시지 출력
        alert(error.message);
      });
  },
  // 데이터 수정 함수
  update() {
    const data = {
      title: document.querySelector('#title').value,
      content: document.querySelector('#content').value,
    };
    const id = document.querySelector('#id').value;

    // fetch API를 이용해 PUT 요청을 보내고 결과 처리
    fetch(`/api/v1/posts/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
        },
        body: JSON.stringify(data),
    })
    .then((response) => {
        if (response.status === 200 || response.status === 201) {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        } else {
            alert('오류가 발생했습니다.');
        }
    })
    .catch((error) => {
        alert(error.message);
    });
  },
  // 데이터 삭제 함수
  delete() {
    const id = document.querySelector('#id').value;
    fetch(`/api/v1/posts/${id}`, {
      method: 'DELETE',
    })
    .then((response) => {
      if (response.status === 200 || response.status === 201) {
        alert('글이 삭제되었습니다.');
        window.location.href = '/';
      } else {
        alert('오류가 발생했습니다.');
      }
    })
    .catch((error) => {
      alert(error.message);
    });
  }
};

// 초기화 함수 호출
main.init();

//var main = {
//    init: function() {
//        var self = this;
//        document.querySelector('#btn-save').addEventListener('click', function() {
//            self.save();
//        });
//    },
//    save: function() {
//        var data = {
//            title: document.querySelector('#title').value,
//            author: document.querySelector('#author').value,
//            content: document.querySelector('#content').value
//        };
//        var xhr = new XMLHttpRequest();
//        xhr.open('POST', '/api/v1/posts');
//        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
//        xhr.onload = function() {
//          if (xhr.status === 200 || xhr.status === 201) {
//            alert('글이 등록되었습니다.');
//            window.location.href = '/';
//          } else {
//            alert('오류가 발생했습니다.');
//          }
//        };
//        xhr.send(JSON.stringify(data));
//    }
//};
//main.init();



//var main = {
//    init : function () {
//        var _this = this;
//        $('#btn-save').on('click', function () {
//            _this.save();
//        });
//    },
//    save : function () {
//        var date = {
//            title: $('#title').val(),
//            author: $('#author').val(),
//            content: $('#content').val()
//        };
//
//        $.ajax({
//            type: 'POST',
//            url: '/api/v1/posts',
//            dataType: 'json',
//            contentType: 'application/json;charset=utf-8',
//            data: JSON.stringify(data)
//        }).done(function() {
//            alert('글이 등록되었습니다.');
//            window.location.href = "/";
//        }).fail(function (error) {
//            alert(JSON.stringify(error));
//        });
//    }
//};
//
//main.init();