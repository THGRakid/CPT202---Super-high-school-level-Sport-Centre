document.addEventListener('DOMContentLoaded', function () {

    const infoContainer = document.querySelector('.info-container');
    let data;


    function loadInfoItems() {
        fetch('/stadium/showLists')
            .then(response => response.json())
            .then(responseData => {
                data = responseData.data; // 从Result对象中提取实际数据
                createInfoItems(data); // 使用获取到的数据创建信息栏
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }

    // 通过staId查看placeId
    function getPlaceIdByStaId(staId) {
        // 发送请求，获取placeId
        // 使用fetch发送GET请求
        fetch(`/api/getPlaceIdByStaId?staId=${staId}`)
            .then(response => response.json())
            .then(data => {
                const placeId = data.placeId;
                // 根据placeId查询所有的时间段
                getTimeSlotsByPlaceId(placeId);
            })
            .catch(error => {
                console.error('Error fetching placeId:', error);
            });
    }

// 根据placeId查询所有的时间段
    function getTimeSlotsByPlaceId(placeId) {
        // 发送请求，获取时间段数据
        // 示例：假设使用fetch发送GET请求
        fetch(`/timeslots/list?placeId=${placeId}`)
            .then(response => response.json())
            .then(data => {
                // 处理时间段数据
                console.log('Time slots:', data);
                // 在这里处理时间段数据的逻辑，例如更新页面显示等
            })
            .catch(error => {
                console.error('Error fetching time slots:', error);
            });
    }

    loadInfoItems();

// 事件监听器，例如按钮点击事件

    document.addEventListener('DOMContentLoaded', function () {
        const buttons = document.querySelectorAll('.sport-button');

        buttons.forEach(button => {
            button.addEventListener('click', function () {
                const staId = this.getAttribute('id'); // 获取按钮的 id，用作 staId
                getPlaceIdByStaId(staId); // 调用函数，并传递对应的 staId
            });
        });
    });

    // 根据后端数据创建信息栏并更新状态
    function createInfoItems(data) {
        infoContainer.innerHTML = '';

        const startIndex = (currentPage - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
        const currentData = data.slice(startIndex, endIndex);

        currentData.forEach((stadium, index) => {
            const infoItem = document.createElement('div');
            infoItem.classList.add('info-item');

            const stadiumNameElement = document.createElement('div');
            stadiumNameElement.classList.add('stadiumName');
            stadiumNameElement.textContent = currentData[index].stadiumName;
            infoItem.appendChild(stadiumNameElement);

            const placeNumberElement = document.createElement('div');
            placeNumberElement.classList.add('placeNumber');
            placeNumberElement.textContent = currentData[index].placeNumber;
            infoItem.appendChild(placeNumberElement);

            const openTimeElement = document.createElement('div');
            openTimeElement.classList.add('openTime');
            openTimeElement.textContent = currentData[index].openTime;
            infoItem.appendChild(openTimeElement);

            const closeTimeElement = document.createElement('div');
            closeTimeElement.classList.add('closeTime');
            closeTimeElement.textContent = currentData[index].closeTime;
            infoItem.appendChild(closeTimeElement);

            const staInfoElement = document.createElement('div');
            staInfoElement.classList.add('staInfo');
            staInfoElement.textContent = currentData[index].staInfo;
            infoItem.appendChild(staInfoElement);


            const limitElement = document.createElement('div');
            limitElement.classList.add('limit');
            limitElement.textContent = currentData[index].limit;
            infoItem.appendChild(limitElement);

            const priceElement = document.createElement('div');
            priceElement.classList.add('price');
            priceElement.textContent = currentData[index].price;
            infoItem.appendChild(priceElement);

            const locationElement = document.createElement('div');
            locationElement.classList.add('location');
            locationElement.textContent = currentData[index].location;
            infoItem.appendChild(locationElement);

            const button = document.createElement('button');
            button.classList.add('info-button');
            button.textContent = 'order';
            button.addEventListener('click', function () {
                if (!document.querySelector('.confirm-box')) {
                    const confirmBox = document.createElement('div');
                    confirmBox.classList.add('confirm-box');
                    confirmBox.innerHTML = `
            <div class="message">Confirm your choice?</div>
            <button class="confirm">Conform</button>
            <button class="cancel">Cancel</button>
          `;
                    document.body.appendChild(confirmBox);

                    const confirmButton = confirmBox.querySelector('.confirm');
                    confirmButton.addEventListener('click', function () {
                        console.log('Confirmed');
                        //到订单页面
                        window.location.href = '#';
                        confirmBox.remove();
                    });

                    const cancelButton = confirmBox.querySelector('.cancel');
                    cancelButton.addEventListener('click', function () {
                        console.log('Cancelled');
                        confirmBox.remove();
                    });
                }
            });

            infoContainer.appendChild(infoItem);
        });
    }


});
  