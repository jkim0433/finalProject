package com.example.rosario.controller;

import com.example.rosario.dto.SellerDto;
import com.example.rosario.entity.Seller;
import com.example.rosario.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;

    }

    @PostMapping("/register")
    public ResponseEntity<Seller> registerSeller(@RequestBody SellerDto sellerDTO) {
        Seller registeredSeller = sellerService.registerSeller(sellerDTO);
        return ResponseEntity.ok(registeredSeller);
    }
}
//import React, { useState } from 'react';
//        import axios from 'axios';
//
//        function RegisterSeller() {
//        const [seller, setSeller] = useState({
//        sellerNm: '',
//        sellerBirthDt: '',
//        sellerCno: '',
//        sellerAdr: '',
//        sellerRgtDt: '',
//        sellerEmailAdr: ''
//        });
//
//        const handleChange = (e) => {
//        const { name, value } = e.target;
//        setSeller({
//        ...seller,
//        [name]: value
//        });
//        };
//
//        const handleSubmit = async (e) => {
//        e.preventDefault();
//        try {
//        const response = await axios.post('/api/sellers/register', seller);
//        console.log(response.data);
//        } catch (error) {
//        console.error('There was an error registering the seller!', error);
//        }
//        };
//
//        return (
//<form onSubmit={handleSubmit}>
//<input name="sellerNm" value={seller.sellerNm} onChange={handleChange} placeholder="Seller Name" required />
//<input type="date" name="sellerBirthDt" value={seller.sellerBirthDt} onChange={handleChange} required />
//<input name="sellerCno" value={seller.sellerCno} onChange={handleChange} placeholder="Contact Number" required />
//<input name="sellerAdr" value={seller.sellerAdr} onChange={handleChange} placeholder="Address" required />
//<input name="sellerRgtDt" value={seller.sellerRgtDt} onChange={handleChange} placeholder="Business Registration Number" required />
//<input name="sellerEmailAdr" value={seller.sellerEmailAdr} onChange={handleChange} placeholder="Email Address" required />
//<button type="submit">Register</button>
//</form>
//        );
//        }
//
//        export default RegisterSeller;
