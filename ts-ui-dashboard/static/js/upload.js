
// let client = new OSS({
//     region: '<oss region>',
//     //阿里云账号AccessKey拥有所有API的访问权限，建议遵循阿里云安全最佳实践，创建并使用STS方式访问API。
//     accessKeyId: '<Your accessKeyId(STS)>',
//     accessKeySecret: '<Your accessKeySecret(STS)>',
//     stsToken: '<Your securityToken(STS)>',
//     bucket: '<Your bucketName>'
// });

Vue.component('file-upload', VueUploadComponent)

new Vue({
    el: '#app',
    components: {
        FileUpload: VueUploadComponent
    },
    data: function () {
        return {
            files: [],
            edit: false,
            cropper: false,
        }
    },
    watch: {
        edit(value) {
            if (value) {
                this.$nextTick(function () {
                    if (!this.$refs.editImage) {
                        return
                    }
                    let cropper = new Cropper(this.$refs.editImage, {
                        aspectRatio: 1 / 1,
                        viewMode: 3,
                        scalable: false,
                        dragCrop: false,
                        zoomable: false,
                    })
                    this.cropper = cropper
                })
            } else {
                if (this.cropper) {
                    this.cropper.destroy()
                    this.cropper = false
                }
            }
        }
    },
    methods: {
        editSave() {
            this.edit = false
            let oldFile = this.files[0]
            let binStr = atob(this.cropper.getCroppedCanvas().toDataURL(oldFile.type).split(',')[1])
            let arr = new Uint8Array(binStr.length)
            for (let i = 0; i < binStr.length; i++) {
                arr[i] = binStr.charCodeAt(i)
            }
            let file = new File([arr], oldFile.name, { type: oldFile.type })
            this.$refs.upload.update(oldFile.id, {
                file,
                type: file.type,
                size: file.size,
                active: true,
            })
        },
        alert(message) {
            alert(message)
        },
        inputFile(newFile, oldFile, prevent) {
            if (newFile && !oldFile) {
                this.$nextTick(function () {
                    this.edit = true
                })
            }
            if (!newFile && oldFile) {
                this.edit = false
            }
            if (newFile && oldFile) {
                // 开始上传
                if (newFile.active !== oldFile.active) {
                    console.log('Start upload', newFile.active, newFile)
                }
                // 上传进度
                if (newFile.progress !== oldFile.progress) {
                    console.log('progress', newFile.progress, newFile)
                }

                // 上传错误
                if (newFile.error !== oldFile.error) {
                    console.log('error', newFile.error, newFile)
                }

                // 上传成功
                if (newFile.success !== oldFile.success) {
                    console.log('success', newFile.success, newFile)
                    // 发送给avatar服务获得的图片连接
                    // $.ajax({
                    //   type: 'GET',
                    //   url: '/file/delete?id=' + oldFile.response.id,
                    // });
                }

            }
        },
        inputFilter(newFile, oldFile, prevent) {
            if (newFile && !oldFile) {
                if (!/\.(gif|jpg|jpeg|png|webp)$/i.test(newFile.name)) {
                    this.alert('Your choice is not a picture')
                    return prevent()
                }
            }
            if (newFile && (!oldFile || newFile.file !== oldFile.file)) {
                newFile.url = ''
                let URL = window.URL || window.webkitURL
                if (URL && URL.createObjectURL) {
                    newFile.url = URL.createObjectURL(newFile.file)
                }
            }
        }
    }
});