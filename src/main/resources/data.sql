CREATE TABLE property (
                          id UUID PRIMARY KEY,
                          name VARCHAR(50),
                          address VARCHAR(200),
                          phone VARCHAR(20),
                          value_per_day NUMERIC (10)
);

CREATE TABLE booking (
                         id UUID PRIMARY KEY,
                         start_date DATE,
                         end_date DATE,
                         guest_name VARCHAR(50),
                         guest_email VARCHAR(100),
                         guest_phone VARCHAR(20),
                         property_id UUID,
                         status VARCHAR(10),
                         FOREIGN KEY (property_id) REFERENCES property(id)

);

CREATE TABLE block (
                       id UUID PRIMARY KEY,
                       start_date DATE,
                       end_date DATE,
                       property_id UUID,
                       FOREIGN KEY (property_id) REFERENCES property(id)
);

-- Inserting a beachfront property
INSERT INTO property (id, name, address, phone, value_per_day)
VALUES ('1f2c87a9-4c35-4cf4-8e4b-5a7d2c96c2a1', 'Sunny Beach House', '123 Ocean Drive, Beach City', '+1 (555) 123-4567', 250);

-- Inserting a cozy cabin
INSERT INTO property (id, name, address, phone, value_per_day)
VALUES ('2ecb487f-79c7-4f8e-ae4b-8cc9f8b1d943', 'Mountain View Cabin', '789 Pine Avenue, Woodsyville', '+1 (555) 987-6543', 175);

-- Inserting a luxury apartment
INSERT INTO property (id, name, address, phone, value_per_day)
VALUES ('3a8a61df-6d45-4231-859e-8e08d74be45e', 'City Skyline Luxury Apartment', '456 Highrise Street, Metroville', '+1 (555) 567-8901', 300);
