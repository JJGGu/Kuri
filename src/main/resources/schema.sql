CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
                       open_id VARCHAR(64) UNIQUE NOT NULL COMMENT '微信OpenID',
                       union_id VARCHAR(64) COMMENT '微信UnionID',
                       nickname VARCHAR(64) COMMENT '用户昵称',
                       avatar_url VARCHAR(255) COMMENT '头像URL',
                       phone VARCHAR(20) COMMENT '手机号',
                       gender TINYINT COMMENT '性别：0未知，1男，2女',
                       country VARCHAR(64) COMMENT '国家',
                       province VARCHAR(64) COMMENT '省份',
                       city VARCHAR(64) COMMENT '城市',
                       vip_level TINYINT DEFAULT 0 COMMENT 'VIP等级：0普通，1VIP，2SVIP',
                       status TINYINT DEFAULT 1 COMMENT '用户状态：0禁用，1正常',
                       last_login_at TIMESTAMP COMMENT '最后登录时间',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

                       INDEX idx_open_id (open_id),
                       INDEX idx_union_id (union_id),
                       INDEX idx_phone (phone),
                       INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';


CREATE TABLE trips (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '行程ID',
                       owner_user_id BIGINT NOT NULL COMMENT '创建用户ID',
                       title VARCHAR(128) NOT NULL COMMENT '行程标题',
                       description TEXT COMMENT '行程描述',
                       cover_image VARCHAR(255) COMMENT '封面图片',

    -- 地点信息
                       departure_location_id VARCHAR(64) COMMENT '出发地POI ID',
                       departure_name VARCHAR(128) NOT NULL COMMENT '出发地名称',
                       destination_location_ids JSON COMMENT '目的地POI ID列表',
                       destination_names JSON COMMENT '目的地名称列表',

    -- 时间信息
                       start_date DATE NOT NULL COMMENT '开始日期',
                       end_date DATE NOT NULL COMMENT '结束日期',
                       days INT NOT NULL COMMENT '行程天数',

    -- 行程配置
                       travel_mode VARCHAR(32) DEFAULT 'Driving' COMMENT '交通方式：Driving/PublicTransport',
                       preferences JSON COMMENT '偏好列表',

    -- 费用信息
                       budget DECIMAL(10,2) COMMENT '预算',
                       estimated_cost DECIMAL(10,2) COMMENT '预估费用',

    -- 状态信息
                       status VARCHAR(32) DEFAULT 'Planning' COMMENT '状态：Planning/Ongoing/Completed/Cancelled',
                       is_public BOOLEAN DEFAULT FALSE COMMENT '是否公开',
                       generation_status TINYINT DEFAULT 0 COMMENT '生成状态：0未生成，1生成中，2已生成，3生成失败',

    -- 统计信息
                       view_count INT DEFAULT 0 COMMENT '浏览次数',
                       like_count INT DEFAULT 0 COMMENT '点赞次数',
                       share_count INT DEFAULT 0 COMMENT '分享次数',

    -- 标签和天气
                       tags JSON COMMENT '标签列表',
                       weather_info JSON COMMENT '天气信息',

                       style VARCHAR(32) DEFAULT NULL COMMENT '行程风格：Relaxed/Adventure/Family/Luxury/Culture/Nature/Custom',
                       people_count INT DEFAULT 1 COMMENT '出行人数',

                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                       deleted_at TIMESTAMP NULL COMMENT '删除时间（软删除）',

                       INDEX idx_owner_user_id (owner_user_id),
                       INDEX idx_status (status),
                       INDEX idx_public (is_public, status),
                       INDEX idx_start_date (start_date),
                       INDEX idx_created_at (created_at),
                       INDEX idx_deleted_at (deleted_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行程表';


CREATE TABLE trip_collaborators (
                                    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '协作记录ID',
                                    trip_id BIGINT NOT NULL COMMENT '行程ID',
                                    user_id BIGINT NOT NULL COMMENT '用户ID',
                                    role VARCHAR(32) NOT NULL COMMENT '角色：Owner/Editor/Viewer',
                                    status VARCHAR(32) DEFAULT 'Active' COMMENT '状态：Active/Pending/Inactive',
                                    permissions JSON COMMENT '权限配置',
                                    invited_by BIGINT COMMENT '邀请人ID',
                                    invited_at TIMESTAMP COMMENT '邀请时间',
                                    joined_at TIMESTAMP COMMENT '加入时间',
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

                                    UNIQUE KEY uk_trip_user (trip_id, user_id),
                                    INDEX idx_trip_id (trip_id),
                                    INDEX idx_user_id (user_id),
                                    INDEX idx_status (status),
                                    INDEX idx_role (role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行程协作者表';


CREATE TABLE trip_days (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '日程ID',
                           trip_id BIGINT NOT NULL COMMENT '行程ID',
                           day_index INT NOT NULL COMMENT '第几天',
                           date DATE NOT NULL COMMENT '具体日期',
                           title VARCHAR(128) COMMENT '当天标题',
                           summary TEXT COMMENT '当天概述',
                           city VARCHAR(64) COMMENT '当天所在城市',
                           theme VARCHAR(128) COMMENT '当天主题',

    -- 天气信息
                           weather_condition VARCHAR(32) COMMENT '天气状况',
                           weather_temperature VARCHAR(16) COMMENT '温度范围',
                           weather_humidity VARCHAR(16) COMMENT '湿度',
                           weather_wind VARCHAR(32) COMMENT '风力',

    -- 起终点信息
                           start_location_name VARCHAR(255) COMMENT '起点名称',
                           start_location_time TIME COMMENT '起点时间',
                           start_location_poi_id VARCHAR(64) COMMENT '起点POI ID',
                           end_location_name VARCHAR(255) COMMENT '终点名称',
                           end_location_time TIME COMMENT '终点时间',
                           end_location_poi_id VARCHAR(64) COMMENT '终点POI ID',

                           estimated_cost DECIMAL(10,2) COMMENT '当日预估费用',
                           is_generated BOOLEAN DEFAULT FALSE COMMENT '是否已生成详细行程',

                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

                           UNIQUE KEY uk_trip_day (trip_id, day_index),
                           INDEX idx_trip_id (trip_id),
                           INDEX idx_date (date),
                           INDEX idx_is_generated (is_generated)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行程日程表';


CREATE TABLE trip_itinerary_items (
                                      id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '行程项目ID',
                                      trip_id BIGINT NOT NULL COMMENT '行程ID',
                                      day_id BIGINT NOT NULL COMMENT '日程ID',
                                      day_index INT NOT NULL COMMENT '第几天',
                                      item_order INT NOT NULL COMMENT '当天顺序',

    -- 基本信息
                                      type VARCHAR(32) NOT NULL COMMENT '类型：Attraction/Accommodation/Food/Transportation',
                                      name VARCHAR(128) NOT NULL COMMENT '名称',
                                      description TEXT COMMENT '描述',

    -- 时间信息
                                      start_time TIME COMMENT '开始时间',
                                      end_time TIME COMMENT '结束时间',
                                      duration INT COMMENT '时长(分钟)',

    -- 位置信息
                                      address VARCHAR(255) COMMENT '详细地址',
                                      city VARCHAR(64) COMMENT '所在城市',
                                      latitude DECIMAL(10,6) COMMENT '纬度',
                                      longitude DECIMAL(10,6) COMMENT '经度',
                                      amap_poi_id VARCHAR(64) COMMENT '高德POI ID',

    -- 费用信息
                                      estimated_cost DECIMAL(10,2) COMMENT '预估费用',

    -- 关联详情表的外键
                                      detail_id BIGINT COMMENT '详情表ID（根据type关联不同表）',

    -- 其他信息
                                      notes TEXT COMMENT '备注',
                                      status VARCHAR(32) DEFAULT 'Active' COMMENT '状态：Active/Cancelled/Completed',

                                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

                                      INDEX idx_trip_id (trip_id),
                                      INDEX idx_day_id (day_id),
                                      INDEX idx_trip_day (trip_id, day_index),
                                      INDEX idx_type (type),
                                      INDEX idx_amap_poi_id (amap_poi_id),
                                      INDEX idx_type_detail (type, detail_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行程项目表';


CREATE TABLE attraction_details (
                                    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '景点详情ID',

    -- 基本信息
                                    name VARCHAR(128) NOT NULL COMMENT '景点名称',
                                    category VARCHAR(64) COMMENT '景点分类：自然风光/历史文化/主题乐园/博物馆等',
                                    level VARCHAR(32) COMMENT '景点等级：5A/4A/3A等',

    -- 详细信息
                                    description TEXT COMMENT '景点描述',
                                    highlights TEXT COMMENT '景点亮点',
                                    history TEXT COMMENT '历史背景',
                                    best_visit_time VARCHAR(255) COMMENT '最佳游览时间',
                                    suggested_duration VARCHAR(64) COMMENT '建议游览时长',
    -- 位置信息
                                    address VARCHAR(255) COMMENT '详细地址',
                                    city VARCHAR(64) COMMENT '所在城市',
                                    latitude DECIMAL(10,6) COMMENT '纬度',
                                    longitude DECIMAL(10,6) COMMENT '经度',
                                    amap_poi_id VARCHAR(64) UNIQUE COMMENT '高德POI ID',


    -- 门票信息
                                    ticket_price DECIMAL(10,2) COMMENT '门票价格',
                                    ticket_info JSON COMMENT '门票详细信息',
                                    booking_url VARCHAR(512) COMMENT '预订链接',

    -- 营业信息
                                    business_hours VARCHAR(255) COMMENT '营业时间',
                                    seasonal_hours JSON COMMENT '季节性营业时间',

    -- 联系信息
                                    contact_phone VARCHAR(64) COMMENT '联系电话',
                                    official_website VARCHAR(255) COMMENT '官方网站',

    -- 评价信息
                                    rating DECIMAL(2,1) COMMENT '评分',
                                    review_count INT DEFAULT 0 COMMENT '评价数量',

    -- 媒体信息
                                    cover_image VARCHAR(255) COMMENT '封面图片',
                                    images JSON COMMENT '图片列表',
                                    videos JSON COMMENT '视频列表',

    -- 设施信息
                                    facilities JSON COMMENT '设施列表：停车场/餐厅/商店/无障碍设施等',
                                    services JSON COMMENT '服务列表：导游/讲解器/行李寄存等',

    -- 交通信息
                                    transportation_guide TEXT COMMENT '交通指南',
                                    parking_info VARCHAR(255) COMMENT '停车信息',

    -- 游览信息
                                    tour_routes JSON COMMENT '推荐游览路线',
                                    tips TEXT COMMENT '游览贴士',
                                    restrictions TEXT COMMENT '游览限制',

    -- 标签
                                    tags JSON COMMENT '标签列表',

    -- 数据来源
                                    data_source VARCHAR(64) COMMENT '数据来源：amap/manual/crawl',
                                    last_verified_at TIMESTAMP COMMENT '最后验证时间',

                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

                                    INDEX idx_amap_poi_id (amap_poi_id),
                                    INDEX idx_category (category),
                                    INDEX idx_level (level),
                                    INDEX idx_rating (rating),
                                    INDEX idx_data_source (data_source)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='景点详情表';


CREATE TABLE accommodation_details (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '酒店详情ID',
                                       amap_poi_id VARCHAR(64) UNIQUE COMMENT '高德POI ID',

    -- 基本信息
                                       name VARCHAR(128) NOT NULL COMMENT '酒店名称',
                                       brand VARCHAR(64) COMMENT '酒店品牌',
                                       category VARCHAR(64) COMMENT '酒店类型：豪华酒店/商务酒店/经济酒店/民宿/青旅等',
                                       star_level VARCHAR(16) COMMENT '星级：五星/四星/三星等',
    -- 位置信息
                                       address VARCHAR(255) COMMENT '详细地址',
                                       city VARCHAR(64) COMMENT '所在城市',
                                       latitude DECIMAL(10,6) COMMENT '纬度',
                                       longitude DECIMAL(10,6) COMMENT '经度',
                                       amap_poi_id VARCHAR(64) UNIQUE COMMENT '高德POI ID',

    -- 详细信息
                                       description TEXT COMMENT '酒店描述',
                                       features TEXT COMMENT '酒店特色',

    -- 价格信息
                                       price_range VARCHAR(64) COMMENT '价格区间',
                                       average_price DECIMAL(10,2) COMMENT '平均价格',
                                       booking_platforms JSON COMMENT '预订平台信息',

    -- 营业信息
                                       check_in_time VARCHAR(32) COMMENT '入住时间',
                                       check_out_time VARCHAR(32) COMMENT '退房时间',

    -- 联系信息
                                       contact_phone VARCHAR(64) COMMENT '联系电话',
                                       official_website VARCHAR(255) COMMENT '官方网站',

    -- 评价信息
                                       rating DECIMAL(2,1) COMMENT '评分',
                                       review_count INT DEFAULT 0 COMMENT '评价数量',
                                       cleanliness_score DECIMAL(2,1) COMMENT '清洁度评分',
                                       service_score DECIMAL(2,1) COMMENT '服务评分',
                                       location_score DECIMAL(2,1) COMMENT '位置评分',

    -- 媒体信息
                                       cover_image VARCHAR(255) COMMENT '封面图片',
                                       images JSON COMMENT '图片列表',
                                       room_images JSON COMMENT '客房图片',

    -- 设施信息
                                       facilities JSON COMMENT '酒店设施：WiFi/停车场/健身房/游泳池/餐厅等',
                                       room_facilities JSON COMMENT '客房设施：空调/电视/冰箱/吹风机等',
                                       services JSON COMMENT '服务项目：24小时前台/行李寄存/洗衣服务等',

    -- 房型信息
                                       room_types JSON COMMENT '房型列表',

    -- 政策信息
                                       policies JSON COMMENT '酒店政策：取消政策/宠物政策/儿童政策等',

    -- 交通信息
                                       transportation_info TEXT COMMENT '交通信息',
                                       nearby_attractions JSON COMMENT '周边景点',

    -- 标签
                                       tags JSON COMMENT '标签列表',

    -- 数据来源
                                       data_source VARCHAR(64) COMMENT '数据来源：amap/booking/manual',
                                       last_verified_at TIMESTAMP COMMENT '最后验证时间',

                                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

                                       INDEX idx_amap_poi_id (amap_poi_id),
                                       INDEX idx_brand (brand),
                                       INDEX idx_category (category),
                                       INDEX idx_star_level (star_level),
                                       INDEX idx_rating (rating),
                                       INDEX idx_price_range (average_price)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='酒店详情表';



CREATE TABLE food_details (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '美食详情ID',
                              amap_poi_id VARCHAR(64) UNIQUE COMMENT '高德POI ID',

    -- 基本信息
                              name VARCHAR(128) NOT NULL COMMENT '餐厅名称',
                              cuisine_type VARCHAR(64) COMMENT '菜系：川菜/粤菜/西餐/日料/韩料等',
                              category VARCHAR(64) COMMENT '餐厅类型：正餐/快餐/咖啡厅/酒吧/小吃等',
    -- 位置信息
                              address VARCHAR(255) COMMENT '详细地址',
                              city VARCHAR(64) COMMENT '所在城市',
                              latitude DECIMAL(10,6) COMMENT '纬度',
                              longitude DECIMAL(10,6) COMMENT '经度',
                              amap_poi_id VARCHAR(64) UNIQUE COMMENT '高德POI ID',
    -- 详细信息
                              description TEXT COMMENT '餐厅描述',
                              specialties TEXT COMMENT '招牌菜',
                              atmosphere VARCHAR(255) COMMENT '餐厅氛围',

    -- 价格信息
                              price_level VARCHAR(32) COMMENT '价格等级：经济实惠/中等价位/高档消费',
                              average_cost DECIMAL(10,2) COMMENT '人均消费',

    -- 营业信息
                              business_hours VARCHAR(255) COMMENT '营业时间',
                              break_time VARCHAR(255) COMMENT '休息时间',

    -- 联系信息
                              contact_phone VARCHAR(64) COMMENT '联系电话',

    -- 评价信息
                              rating DECIMAL(2,1) COMMENT '评分',
                              review_count INT DEFAULT 0 COMMENT '评价数量',
                              taste_score DECIMAL(2,1) COMMENT '口味评分',
                              environment_score DECIMAL(2,1) COMMENT '环境评分',
                              service_score DECIMAL(2,1) COMMENT '服务评分',

    -- 媒体信息
                              cover_image VARCHAR(255) COMMENT '封面图片',
                              images JSON COMMENT '图片列表',
                              menu_images JSON COMMENT '菜单图片',

    -- 菜单信息
                              menu_items JSON COMMENT '菜单项目',
                              recommended_dishes JSON COMMENT '推荐菜品',

    -- 设施信息
                              facilities JSON COMMENT '设施：包厢/WiFi/停车位/儿童座椅等',
                              services JSON COMMENT '服务：外卖/预订/刷卡/移动支付等',

    -- 特殊信息
                              dietary_options JSON COMMENT '饮食选择：素食/清真/无麸质等',
                              suitable_occasions JSON COMMENT '适合场合：商务宴请/家庭聚餐/约会等',

    -- 预订信息
                              reservation_required BOOLEAN DEFAULT FALSE COMMENT '是否需要预订',
                              booking_platforms JSON COMMENT '预订平台',

    -- 标签
                              tags JSON COMMENT '标签列表',

    -- 数据来源
                              data_source VARCHAR(64) COMMENT '数据来源：amap/dianping/manual',
                              last_verified_at TIMESTAMP COMMENT '最后验证时间',

                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

                              INDEX idx_amap_poi_id (amap_poi_id),
                              INDEX idx_cuisine_type (cuisine_type),
                              INDEX idx_category (category),
                              INDEX idx_price_level (price_level),
                              INDEX idx_rating (rating),
                              INDEX idx_average_cost (average_cost)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='美食详情表';


CREATE TABLE transportation_details (
                                        id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '交通详情ID',

    -- 基本信息
                                        mode VARCHAR(32) NOT NULL COMMENT '交通方式：Walking/Driving/PublicTransport/Flight/Train/Taxi',
                                        provider VARCHAR(64) COMMENT '服务提供商：高德/滴滴/12306/航空公司',

    -- 起终点信息
                                        from_name VARCHAR(255) NOT NULL COMMENT '起点名称',
                                        from_latitude DECIMAL(10,6) COMMENT '起点纬度',
                                        from_longitude DECIMAL(10,6) COMMENT '起点经度',
                                        from_poi_id VARCHAR(64) COMMENT '起点POI ID',

                                        to_name VARCHAR(255) NOT NULL COMMENT '终点名称',
                                        to_latitude DECIMAL(10,6) COMMENT '终点纬度',
                                        to_longitude DECIMAL(10,6) COMMENT '终点经度',
                                        to_poi_id VARCHAR(64) COMMENT '终点POI ID',

    -- 路线信息
                                        distance DECIMAL(10,2) COMMENT '距离(km)',
                                        duration INT COMMENT '预计时长(分钟)',
                                        route_description TEXT COMMENT '路线描述',
                                        route_steps JSON COMMENT '详细路线步骤',

    -- 费用信息
                                        base_cost DECIMAL(10,2) COMMENT '基础费用',
                                        estimated_cost DECIMAL(10,2) COMMENT '预估总费用',
                                        cost_breakdown JSON COMMENT '费用明细',

    -- 时刻表信息（公共交通/航班/火车）
                                        departure_time TIME COMMENT '出发时间',
                                        arrival_time TIME COMMENT '到达时间',
                                        schedule_info JSON COMMENT '班次信息',

    -- 预订信息
                                        booking_required BOOLEAN DEFAULT FALSE COMMENT '是否需要预订',
                                        booking_url VARCHAR(512) COMMENT '预订链接',
                                        booking_platforms JSON COMMENT '预订平台列表',

    -- 导航信息
                                        navigation_url VARCHAR(512) COMMENT '导航链接',
                                        amap_route_id VARCHAR(128) COMMENT '高德路线ID',

    -- 备注信息
                                        tips TEXT COMMENT '出行提示',
                                        restrictions TEXT COMMENT '限制说明',

    -- 数据来源
                                        data_source VARCHAR(64) COMMENT '数据来源：amap/baidu/manual',
                                        last_verified_at TIMESTAMP COMMENT '最后验证时间',

                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

                                        INDEX idx_mode (mode),
                                        INDEX idx_from_to_poi (from_poi_id, to_poi_id),
                                        INDEX idx_data_source (data_source)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='交通详情表';


CREATE TABLE trip_invitations (
                                  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '邀请ID',
                                  trip_id BIGINT NOT NULL COMMENT '行程ID',
                                  inviter_user_id BIGINT NOT NULL COMMENT '邀请人ID',
                                  invitee_user_id BIGINT COMMENT '被邀请用户ID（如果已注册）',
                                  invitee_phone VARCHAR(20) COMMENT '被邀请人手机号',
                                  role VARCHAR(32) NOT NULL COMMENT '邀请角色：Editor/Viewer',
                                  message TEXT COMMENT '邀请消息',
                                  status VARCHAR(32) DEFAULT 'Pending' COMMENT '状态：Pending/Accepted/Rejected/Expired',
                                  expires_at TIMESTAMP COMMENT '过期时间',
                                  processed_at TIMESTAMP COMMENT '处理时间',
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

                                  INDEX idx_trip_id (trip_id),
                                  INDEX idx_inviter_user_id (inviter_user_id),
                                  INDEX idx_invitee_user_id (invitee_user_id),
                                  INDEX idx_invitee_phone (invitee_phone),
                                  INDEX idx_status (status),
                                  INDEX idx_expires_at (expires_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行程邀请表';


CREATE TABLE ai_model_calls (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '调用记录ID',
                                user_id BIGINT NOT NULL COMMENT '用户ID',
                                trip_id BIGINT COMMENT '关联行程ID',
                                call_type VARCHAR(32) COMMENT '调用类型：trip_generate/trip_update/day_generate',
                                model_name VARCHAR(64) COMMENT '模型名称',
                                prompt TEXT COMMENT '输入提示词',
                                response TEXT COMMENT 'AI返回结果',
                                status TINYINT DEFAULT 0 COMMENT '状态：0处理中，1成功，2失败',
                                error_message VARCHAR(255) COMMENT '错误信息',
                                tokens_used INT COMMENT '使用的token数量',
                                cost DECIMAL(10,4) COMMENT '调用费用',
                                duration_ms INT COMMENT '调用耗时(毫秒)',
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

                                INDEX idx_user_id (user_id),
                                INDEX idx_trip_id (trip_id),
                                INDEX idx_call_type (call_type),
                                INDEX idx_status (status),
                                INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI模型调用记录表';


CREATE TABLE guides (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '攻略唯一ID',
                        title VARCHAR(255) NOT NULL COMMENT '攻略标题',
                        url VARCHAR(1024) NOT NULL COMMENT '原始链接',
                        cover_image VARCHAR(1024) COMMENT '封面图片URL',
                        description TEXT COMMENT '攻略摘要/描述',
                        type VARCHAR(32) COMMENT '攻略类型（Food/Hotel/Attraction/Other）',
                        platform VARCHAR(32) COMMENT '平台：xiaohongshu/ctrip/mafengwo/dianping/qunar/meituan/official/user',
                        city VARCHAR(64) COMMENT '关联城市',
                        attractions JSON COMMENT '景点列表（结构化JSON）',
                        foods JSON COMMENT '美食列表（结构化JSON）',
                        hotels JSON COMMENT '住宿列表（结构化JSON）',
                        poi_ids JSON COMMENT '涉及POI ID列表',
                        content TEXT COMMENT '正文内容',
                        tags JSON COMMENT '标签',
                        created_by BIGINT COMMENT '创建用户ID（如为用户上传）',
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        deleted_at TIMESTAMP NULL COMMENT '删除时间（软删除）',
                        INDEX idx_city (city),
                        INDEX idx_type (type),
                        INDEX idx_platform (platform),
                        INDEX idx_created_by (created_by),
                        INDEX idx_deleted_at (deleted_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户/平台攻略表';


