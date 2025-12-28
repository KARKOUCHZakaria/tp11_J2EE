import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';
import { CategoryService } from '../../categories/category.service';
import { Product } from '../product';
import { Category } from '../../categories/category';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './product-form.html',
  styleUrl: './product-form.css',
})
export class ProductFormComponent implements OnInit {
  productForm: FormGroup;
  isEditMode = false;
  productId?: number;
  categories: Category[] = [];

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private categoryService: CategoryService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      price: [0, [Validators.required, Validators.min(0)]],
      stock: [0, [Validators.required, Validators.min(0)]],
      categoryId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadCategories();

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.productId = +id;
      this.loadProduct(this.productId);
    }
  }

  loadCategories() {
    this.categoryService.list().subscribe(categories => {
      this.categories = categories;
    });
  }

  loadProduct(id: number) {
    this.productService.get(id).subscribe(product => {
      this.productForm.patchValue({
        name: product.name,
        price: product.price,
        stock: product.stock,
        categoryId: product.category?.id || ''
      });
    });
  }

  onSubmit() {
    if (this.productForm.invalid) {
      return;
    }

    const formValue = this.productForm.value;
    const product: Product = {
      name: formValue.name,
      price: formValue.price,
      stock: formValue.stock,
      category: { id: +formValue.categoryId } as Category
    };

    if (this.isEditMode && this.productId) {
      this.productService.update(this.productId, product).subscribe(() => {
        this.router.navigate(['/products']);
      });
    } else {
      this.productService.create(product).subscribe(() => {
        this.router.navigate(['/products']);
      });
    }
  }

  cancel() {
    this.router.navigate(['/products']);
  }
}
