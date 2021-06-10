# Homey_Mobil_Uygulama
Apart ve yurtlar için mobil uygulama

Bu uygulama benim üniversitedeki bitirme projem olmakla beraber ileride geliştirip mobil platformlarda yayınlayacağım uygulamadır.

Backend: Java Spring Boot
Mobil : React - Native
Frontend(Gelecekte yapılacak) : React

Uygulama Özeti : 
Günümüzde birçok insan, genellikle öğrenci olanlar apartlarda ve yurtlarda kalmaktadır. 
Bu yerlerde kalırken bir şikayetleri olduğu zaman ya da oradaki araç gereçlerden birisini kullanmak istedikleri zaman sıkıntı yaşayabiliyorlardı. 
Geliştirdiğim uygulama ile birlikte apart ve yurtlarda kalan insanlar rahat bir şekilde şikayetlerini belirtip doğrudan yöneticiye dertlerini ulaştırabilecekler 
ve aynı zamanda sınırlı sayıda olan araç gereçleri örneğin bir çamaşır makinesini tarih ve saat belirleyerek rezervasyon oluşturup kullanabilecekler.

Geliştirdiğim mobil uygulamanın veritabanı olarak PostgreSQL kullandım. Mobil uygulama kısmında ise React-Native teknolojisini kullandım. 
Uygulamanın backendini de Java : Spring Boot ile geliştirdim. Uygulamayı geliştirirken güvenliğe önem verdiğim için Spring Boot Security teknolojisini de kullandım. 
Bu teknoloji sayesinde uygulamayı kullanan kişiler giriş yaparken bir token alıyor ve bu token ile uygulama içerisinde ki diğer işlemlerini gerçekleştiriyor. 
Token olmayan istekler backende gittiğinde hiçbir şekilde servislere ulaşamıyor.

<img width="318" alt="homey_login_page" src="https://user-images.githubusercontent.com/57543913/121527500-24708a80-ca03-11eb-98d2-6bc25a84a254.png">
