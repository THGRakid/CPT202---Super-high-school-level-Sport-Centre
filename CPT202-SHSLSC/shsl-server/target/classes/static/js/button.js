const mapButton = document.getElementById('mapButton');
        const phoneButton = document.getElementById('phoneButton');
        const feedbackButton = document.getElementById('feedbackButton');
        const mapModal = document.getElementById('mapModal');
        const phoneModal = document.getElementById('phoneModal');
        const feedbackModal = document.getElementById('feedbackModal');
        const overlay = document.getElementById('overlay');
        const feedbackForm = document.getElementById('feedbackForm');

        mapButton.addEventListener('click', () => {
            mapModal.style.display = 'block'; // 显示地图弹窗
            overlay.style.display = 'block'; // 显示遮罩层
        });

        phoneButton.addEventListener('click', () => {
            phoneModal.style.display = 'block'; // 显示电话弹窗
            overlay.style.display = 'block'; // 显示遮罩层
        });

        feedbackButton.addEventListener('click', () => {
            feedbackModal.style.display = 'block'; // 显示反馈弹窗
            overlay.style.display = 'block'; // 显示遮罩层
        });

        overlay.addEventListener('click', () => {
            mapModal.style.display = 'none'; // 隐藏地图弹窗
            phoneModal.style.display = 'none'; // 隐藏电话弹窗
            feedbackModal.style.display = 'none'; // 隐藏反馈弹窗
            overlay.style.display = 'none'; // 隐藏遮罩层
        });

        feedbackForm.addEventListener('submit', async (e) => {
            e.preventDefault(); // 阻止表单默认提交行为

            const formData = new FormData(feedbackForm); // 创建 FormData 对象

            try {
                const response = await fetch('/submit-feedback', {
                    method: 'POST',
                    body: formData
                });

                if (response.ok) {
                    alert('反馈提交成功！');
                    feedbackForm.reset(); // 清空表单
                } else {
                    throw new Error('反馈提交失败！');
                }
            } catch (error) {
                console.error(error);
                alert('发生错误，请稍后再试！');
            }
        });