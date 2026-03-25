package org.zx.tree.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zx.tree.entity.Reflection;
import org.zx.tree.repository.ReflectionRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ReflectionRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年M月d日 HH:mm:ss"));
                
                List<Reflection> reflections = List.of(
                    createReflection("赵文博同学", "《希望的种子》", "我的祖辈都是农民，父亲更是种了一辈子的地。从小，我就跟着父亲在田里劳作，春种秋收，寒来暑往。记得每年春天，父亲都会带着我去村后的山坡上种树。他说：\"人活一世，总要留下点什么。种下的树，就是留给子孙最好的财富。\"那时候，我还小，不懂这句话的含义。\n\n记得有一年春天，父亲带着我在山坡上种树苗。我问他：\"爹，为什么要在山上种树啊？\"父亲笑着说：\"前人栽树，后人乘凉。等你长大了，这些树就长高了，那时候你就能乘凉了。\"那时候种下的树苗，现在已经长成大树了。每次回家路过那片山坡，我都会想起父亲慈祥的笑容。\n\n后来，我选择了农学专业，想系统地学习农业知识。在学校里，我学习了作物育种学、作物栽培学、农业生态学等课程。我深刻认识到，现代农业的发展离不开科技创新，而绿色发展是农业可持续发展的必然选择。\n\n\"绿水青山就是金山银山\"——父亲说的话，和总书记说的一样有理。\n\n去年暑假，我参加学校组织的农业调研活动，走访了很多乡村。我发现，一些地方在追求高产的过程中，忽视了环境保护，导致土壤酸化、水源污染等问题。这让我意识到，农业发展必须走绿色发展之路，不能以牺牲环境为代价换取暂时的产量增长。\n\n作为农学专业的学生，我毕业后想回到家乡，成为一名新型职业农民。我要用所学的专业知识，发展绿色农业，种植优质农产品，打造自己的农业品牌。我还要带动村民们一起植树造林，建设农田林网，改善农业生态环境。\n\n我要让的土地更加肥沃，让乡村更加美丽。我相信，只要我们每个人都认识到环境保护的重要性，从自身做起，从小事做起，我们的家乡一定会重新焕发生机。\n\n每一粒种子都是希望的象征，每一棵树都是未来的期待。让我们一起携手，在乡村的土地上播撒希望的种子，让绿色成为乡村最靓丽的底色！",
                        "https://images.unsplash.com/photo-1592419044706-39796d40d98c?w=800", "每一粒种子都蕴含着希望",
                        "https://images.unsplash.com/photo-1500595046743-cd271d694d30?w=800", "家乡的农田风光",
                        "https://images.unsplash.com/photo-1625246333195-78d9c38ad449?w=800", "现代农业技术",
                        currentDate, "\"绿水青山就是金山银山\"——父亲说的话，和总书记说的一样有理。", true),
                    
                    createReflection("陈思雨同学", "《让绿色成为乡村最靓丽的底色》", "春天，是播种希望的季节。在这个植树节，我深刻体会到了绿色对于乡村发展的重要意义。\n\n我的家乡是一个普通的小村庄，过去，村子周围都是光秃秃的山丘，每到夏天，太阳炙烤着大地，村民们只能在树下寻找片刻的阴凉。那时候，我常常听爷爷讲述他年轻时植树的故事，那时候的山上到处都是郁郁葱葱的森林。\n\n近年来，在国家政策的支持下，我们村开始大规模的植树造林活动。村民们积极响应号召，每家每户都在自己的责任山上种下了树苗。经过几年的努力，曾经的光秃山丘如今已经变成了一片绿色的海洋。\n\n植树不仅美化了环境，更带来了可观的经济效益。山上的果树长大后，村民们可以采摘果实出售，增加收入。林下经济也随之发展起来，村民们在林中种植中药材，养殖家禽，实现了多重收益。\n\n作为一名大学生，我深感自己肩负的责任。我们要用所学知识，为家乡的绿色发展贡献力量。我希望将来能够回到家乡，将所学知识运用到农业发展中，让绿色成为乡村最靓丽的底色！",
                        "https://images.unsplash.com/photo-1502082553048-f009c37129b9?w=800", "绿意盎然的乡村",
                        null, null, null, null,
                        currentDate, "绿水青山就是金山银山", true),
                    
                    createReflection("林浩宇同学", "《根植大地，绿满乡野》", "每年的3月12日是植树节，这是一个播撒绿色、播种希望的日子。对于我们这些来自农村的孩子来说，植树节有着特殊的意义。\n\n我的父亲是一名护林员，从小就教育我要爱护一草一木。他说：\"树是土地的守护者，没有树，就没有水，没有我们人类的生存家园。\"这句话深深地印在了我的脑海里。\n\n记得小时候，每到植树节，父亲都会带着我去山上种树。他教我如何挖坑、如何培土、如何浇水。每一棵树苗，都像是我们的孩子，需要细心呵护。二十多年过去了，当年种下的树苗已经长成参天大树，我也从懵懂少年成长为一名大学生。\n\n如今，我选择了林业大学，立志成为一名林业工作者。我希望能够用自己的专业知识，帮助更多的乡村实现绿色发展。我要推广先进的林业技术，让更多的荒山变绿洲，让更多的乡村焕发新的生机。\n\n根植大地，绿满乡野。这不仅是我的梦想，更是我们这一代人的使命。让我们携手共进，为建设美丽中国贡献自己的力量！",
                        "https://images.unsplash.com/photo-1542601906990-b4d3fb778b09?w=800", "茂密的森林",
                        "https://images.unsplash.com/photo-1516214104703-d870798883c5?w=800", "植树活动",
                        null, null,
                        currentDate, "树是土地的守护者", true),
                    
                    createReflection("刘欣悦同学", "《用双手描绘乡村的绿色画卷》", "在我的记忆里，乡村总是与绿色紧密相连。金黄色的麦田、碧绿的菜畦、郁郁葱葱的果树……这些构成了我对乡村最美好的印象。\n\n然而，随着工业化的快速发展，一些乡村的生态环境受到了破坏。溪水不再清澈，天空不再湛蓝，这让从小在农村长大的我感到十分痛心。我开始思考，如何才能让乡村重新焕发绿色生机？\n\n去年寒假，我参加了学校组织的\"乡村振兴\"调研活动。我们走访了多个乡村，了解当地的生态保护情况。在一个偏远的小山村，我遇到了一位七十多岁的老支书。他带领村民们坚持植树三十年，把一座荒山变成了绿林。\n\n老支书告诉我们：\"只要肯动手，荒山也能变绿洲。\"这句话让我深受触动。回来后，我开始关注乡村绿化，积极参与各种环保活动。我还利用所学知识，帮助家乡的农民改进种植技术，减少农药化肥的使用，保护土地资源。\n\n我相信，只要每个人都贡献一份力量，我们的乡村一定会变得更加美丽。让我们用双手描绘乡村的绿色画卷，让绿色成为乡村振兴的底色！",
                        "https://images.unsplash.com/photo-1466692476868-aef1dfb1e735?w=800", "绿色乡村",
                        null, null, null, null,
                        currentDate, "只要肯动手，荒山也能变绿洲", true),
                    
                    createReflection("王梦琪同学", "《生态养殖，绿色发展》", "我的家乡是一个以农业为主的村庄，主要种植粮食作物和果树。近年来，随着人们对绿色食品的需求增加，村里开始发展生态养殖业。\n\n所谓生态养殖，就是利用自然生态系统进行养殖生产，不使用化肥和农药，让动物在自然环境中生长。这种养殖方式不仅提高了产品质量，也保护了生态环境。\n\n我家开始尝试生态养殖是在三年前。当时，爸爸在电视上看到其他地方发展生态养殖的成功案例，决定在我们的果园里散养鸡鸭。起初，我还有些担心，不用饲料的鸡鸭能长大吗？\n\n然而，实践证明，生态养殖的鸡鸭因为运动量大，肉质更加鲜美，下的蛋也更有营养。虽然生长周期长一些，但价格却高出普通产品不少。如今，我们家的生态养殖已经初具规模，每年都能带来可观的收入。\n\n更让我欣喜的是，果园里的果树因为有了鸡鸭的粪便作为天然肥料，长得更加茂盛，果实也更甜了。这种循环农业模式，实现了经济和环境的双赢。\n\n我相信，生态养殖是乡村绿色发展的重要途径。我要继续学习这方面的知识，帮助更多的村民发展生态养殖，让我们的乡村更绿、更美、更富！",
                        "https://images.unsplash.com/photo-1548550023-2bdb3c5beed7?w=800", "生态养殖",
                        null, null, null, null,
                        currentDate, "生态养殖实现了经济和环境的双赢", true),
                    
                    createReflection("张俊杰同学", "《科技兴农，绿色先行》", "作为农业院校的学生，我深知科技对农业发展的重要性。在学习过程中，我一直在思考如何将现代科技与绿色农业相结合，推动乡村可持续发展。\n\n去年暑假，我参加了学校的\"科技下乡\"活动，来到一个贫困村帮助农民解决农业生产中遇到的问题。让我印象深刻的是，村民们仍然沿用着传统的种植方式，产量低、效益差。\n\n我利用所学知识，帮助村民们引进了新的种植技术。我们采用了测土配方施肥技术，根据土壤成分精准施肥，既减少了化肥的使用量，又提高了作物产量。我们还推广了生物防治技术，利用天敌来防治害虫，减少了农药的使用。\n\n一年过去了，这个村庄发生了显著变化。粮食产量提高了20%，农药化肥使用量减少了30%，村民们的收入明显增加。更重要的是，土壤和水源得到了有效保护，农业生产变得更加可持续。\n\n这次实践让我深刻认识到，科技是推动绿色农业发展的关键力量。作为新时代的农业学子，我们要努力学习专业知识，将先进技术和绿色理念带到农村去，为乡村振兴贡献青春力量！",
                        "https://images.unsplash.com/photo-1530836369250-ef72a3f5cda8?w=800", "科技农业",
                        "https://images.unsplash.com/photo-1464226184884-fa280b87c399?w=800", "绿色农田",
                        null, null,
                        currentDate, "科技是推动绿色农业发展的关键力量", true)
                );
                
                repository.saveAll(reflections);
                System.out.println("=== 初始化数据已导入：6条感悟 ===");
            } else {
                System.out.println("=== 数据库已有数据，跳过初始化 ===");
            }
        };
    }
    
    private Reflection createReflection(String author, String title, String content, 
            String img1, String caption1, String img2, String caption2, String img3, String caption3,
            String date, String quote, boolean published) {
        Reflection r = new Reflection();
        r.setAuthorName(author);
        r.setTitle(title);
        r.setContent(content);
        r.setImageUrl(img1);
        r.setImageCaption(caption1);
        r.setSecondImageUrl(img2);
        r.setSecondImageCaption(caption2);
        r.setThirdImageUrl(img3);
        r.setThirdImageCaption(caption3);
        r.setPublishDate(date);
        r.setHighlightQuote(quote);
        r.setPublished(published);
        return r;
    }
}