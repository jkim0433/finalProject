package com.example.rosario.controller;

import com.example.rosario.dto.CustomerDto;
import com.example.rosario.entity.Customer;
import com.example.rosario.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.registerCustomer(customerDto);
        return ResponseEntity.ok(customer);
    }
}
//import React, { useState } from 'react';
//        import axios from 'axios';
//        import { useHistory } from 'react-router-dom';
//
//        function RegisterPage() {
//        const [customer, setCustomer] = useState({
//        customerNm: '',
//        customerBirthDt: '',
//        customerCno: '',
//        customerAdr: '',
//        customerEmlAdr: '',
//        customerPassword: '',
//        });
//        const history = useHistory();
//
//        const handleChange = (e) => {
//        const { name, value } = e.target;
//        setCustomer((prevState) => ({
//        ...prevState,
//        [name]: value,
//        }));
//        };
//
//        const handleRegister = async (event) => {
//        event.preventDefault();
//        try {
//        await axios.post('/api/customers/register', customer); // 수정된 엔드포인트
//        // 회원가입 성공 후 로그인 페이지로 이동
//        history.push('/login');
//        } catch (error) {
//        console.error('회원가입 오류:', error);
//        }
//        };
//
//        return (
//<div>
//<h2>회원가입</h2>
//<form onSubmit={handleRegister}>
//<div>
//<label htmlFor="customerNm">이름:</label>
//<input
//            type="text"
//                    id="customerNm"
//                    name="customerNm"
//                    value={customer.customerNm}
//                    onChange={handleChange}
//                    />
//</div>
//<div>
//<label htmlFor="customerBirthDt">생년월일:</label>
//<input
//            type="date"
//                    id="customerBirthDt"
//                    name="customerBirthDt"
//                    value={customer.customerBirthDt}
//                    onChange={handleChange}
//                    />
//</div>
//<div>
//<label htmlFor="customerCno">연락처:</label>
//<input
//            type="text"
//                    id="customerCno"
//                    name="customerCno"
//                    value={customer.customerCno}
//                    onChange={handleChange}
//                    />
//</div>
//<div>
//<label htmlFor="customerAdr">주소:</label>
//<input
//            type="text"
//                    id="customerAdr"
//                    name="customerAdr"
//                    value={customer.customerAdr}
//                    onChange={handleChange}
//                    />
//</div>
//<div>
//<label htmlFor="customerEmlAdr">이메일:</label>
//<input
//            type="email"
//                    id="customerEmlAdr"
//                    name="customerEmlAdr"
//                    value={customer.customerEmlAdr}
//                    onChange={handleChange}
//                    />
//</div>
//<div>
//<label htmlFor="customerPassword">비밀번호:</label>
//<input
//            type="password"
//                    id="customerPassword"
//                    name="customerPassword"
//                    value={customer.customerPassword}
//                    onChange={handleChange}
//                    />
//</div>
//<button type="submit">회원가입</button>
//</form>
//</div>
//        );
//        }
//
//        export default RegisterPage;
