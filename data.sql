CREATE TABLE eligibility_records (
    member_unique_id TEXT PRIMARY KEY,
    first_name TEXT,
    last_name TEXT,
    date_of_birth TEXT,
    gender TEXT,
    relations TEXT,
    sub_group TEXT,
    job_type TEXT,
    hire_date TEXT,
    eligibility_start_date TEXT,
    eligibility_end_date TEXT,
    employee_status TEXT,
    phone_number TEXT,
    address_1 TEXT,
    address_2 TEXT,
    city TEXT,
    state_code TEXT,
    zip_code TEXT,
    country TEXT,
    employee_group TEXT
);

INSERT INTO eligibility_records (
    member_unique_id, first_name, last_name, date_of_birth, gender,
    relations, sub_group, job_type, hire_date, eligibility_start_date,
    eligibility_end_date, employee_status, phone_number, address_1,
    address_2, city, state_code, zip_code, country, employee_group
) VALUES
('44000100', 'Walter', 'Jacobson', '1979-01-09', 'M',
 'EE', 'P001', 'Support', '2010-06-15', '2023-01-01',
 NULL, 'Active', '555-1234', '123 Main St', NULL, 'New York',
 'NY', '10001', 'USA', 'Group A');

