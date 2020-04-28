import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupComponent } from './signup.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('SignupComponent', () => {
  let component: SignupComponent;
  let fixture: ComponentFixture<SignupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignupComponent ],
      imports: [ReactiveFormsModule, HttpClientTestingModule, RouterTestingModule,FormsModule, HttpClientModule, RouterModule.forRoot([])]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

 

  it('created a form with username and password with login button', () => {
    const userNameContainer = fixture.debugElement.nativeElement.querySelector('#username');
    const passwordContainer = fixture.debugElement.nativeElement.querySelector('#password');
    const contactContainer = fixture.debugElement.nativeElement.querySelector('#contactNo');
    const emailContainer = fixture.debugElement.nativeElement.querySelector('#emailId');

    expect(userNameContainer).toBeDefined();
    expect(passwordContainer).toBeDefined();
    expect(contactContainer).toBeDefined();
    expect(emailContainer).toBeDefined();
  });

});
