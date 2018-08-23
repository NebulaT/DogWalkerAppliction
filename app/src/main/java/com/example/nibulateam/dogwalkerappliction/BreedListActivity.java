package com.example.nibulateam.dogwalkerappliction;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nibulateam.dogwalkerappliction.Model.PetPackage.Pet_Package.Pet;
import com.example.nibulateam.dogwalkerappliction.Model.User;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BreedListActivity extends Activity {


    private User user;
    private String dogName;
    private Uri dogPhoto;

    //UI//
    private CustomAdapter customAdapter;
    private EditText searchBarTextView;
    private ImageView bottomImageView;
    private ImageView topImageView;
    private TextView divider;
    private CheckBox mixedBreedCheckBox;
    private Button selectButton;

    private int position=0;

    int[] dogBreedImage = new int[]{R.drawable.dog_two};

    String[] dogBreedNames = {"Affenpinscher","Afghan Hound","Airedale Terrier", "Akita Inu","Alaskan Malamute","Am Staff","American Coonhound","American Eskimo Dog"
            ,"American Foxhound","American Pitbull Terrier","American Water Spaniel","Anatolian Shepherd", "Australian Cattle","Australian Shepherd","Australian Terrier","Azawakh","Basenji","Basset Hound","Beagle",
            "Bearded Collie","Beauceron","Bedlington Terrier","Belgian Laekenois","Belgian Malinois","Belgian Sheepdog","Belgian Tervuren","Bergamasco","Bernese Mountain Dog,",
            "Bichon Frisé","Biewer Terrier","Black & Tan Coonhound","Black Russian Terrier","Bloodhound","Bluetick Coonhound","Boerboel","Border Collie","Border Terrier","Borzoi","Boston Terrier","Bouvier des Flandres","Boxer","Briard",
            "Brittany","Brussels Griffon","Bull Terrier","Bulldog - American","Bullmastiff","Cairn Terrier","Canaan Dog","Cane Corse","Cardigan Welsh Corgi","Carolina Dog","Cavalier King Charles","Cesky Terrier","Chesapeake Bay Retriever","Chihuahua","Chinese Crested",
            "Chinese Shar-Pei","Chinook","Chow Chow","Cirneco dell'Etna","Clumber Spaniel","Cocker Spaniel - American","Cocker Spaniel - English","Collie","Curly-Coated Retriever","Dachshund","Dalmatian","Dandie Dinmont Terrier"
            ,"Doberman Pinscher","Dogo Argentino","English Bulldog","English Foxhound","English Pointer","English Setter","English Toy Spaniel","Entlebucher Mountain Dog",
            "Field Spaniel","Fila Brasileiro","Finnish Lapphund","Finnish Spitz","Flat Coated Retriever","French Bulldog","German Pinscher","German Shepherd"," German Shorthaired Pointer","German Wirehaired Pointer","Giant Schnauzer","Glen of Imaal Terrier",
            "Golden Retriever","Goldendoodle","Gordon Setter","Great Dane","Great Pyrenees","Greater Swiss Mountain Dog","Greyhound","Harrier","Havana Silk Dog",
            "Havanese","Ibizan Hound","Icelandic Sheepdog","Irish Setter","Irish Terrier",
            "Irish Water Spaniel","Irish Wolfhound","Italian Greyhound","Jack Russell Terrier",
            "Japanese Chin","Keeshond","Kerry Blue Terrier","Komondor","Kuvasz","Labradoodle",
            "Labrador Retriever","Lakeland Terrier","Lhasa Apso","Lowchen","Maltese","Manchester Terrier","Mastiff","Miniature Bull Terrier","Miniature Pinscher","Miniature Schnauzer","NAID Breed","Neapolitan Mastiff",
            "Newfoundland","Norfolk Terrier","Norwegian Buhund","Norwegian Elkhound","Norwegian Lundehund ","Norwich Terrier","Nova Scotia Duck Tolling","Old English Sheepdog ","Otterhound ","Papillon ",
            "Parson Russell Terrier","Pekingese ","Pembroke Welsh Corgi ","Peruvian Inca Orchid ", "Petit Basset Griffon Vendéen","Pharaoh Hound ","Plott Hound ","Polish Lowland ",
            "Pomeranian","Poodle - Standard ","Poodle - Toy ","Portuguese Podengo Pequeno","Pug", "Puli","Pumi","Pyrenean Shepherd","Rat Terrier","Redbone Coonhound ","Rhodesian Ridgeback",
            "Rottweiler","Saint Bernard ","Saluki ","Samoyed ","Schipperke ","Scottish Deerhound", "Scottish Terrier","Sealyham Terrier","Shetland Sheepdog ","Shiba Inu",
            "Shih Tzu","Siberian Husky","Silky Terrier","Skye Terrier","Sloughi ","Smooth Fox Terrier ","Soft Coated Wheaten Terrier ","Spinone Italiano","Springer Spaniel - English ","Staffordshire Bull Terrier ","Standard Schnauzer ",
            "Sussex Spaniel","Swedish Vallhund ","Tibetan Mastiff","Tibetan Spaniel", "Tibetan Terrier ","Toy Fox Terrier","Treeing Walker Coonhound","Vizsla ", "Weimaraner ","Welsh Springer Spaniel ","Welsh Terrier",
            "West Highland White Terrier", "Whippet ","Wire Fox Terrier","Wirehaired Pointing Griffon ","Wirehaired Vizsla", "Xoloitzcuintli ","Yorkshire Terrier "};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breedlist);

        DisplayMetrics popUpList=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(popUpList);

        int width=popUpList.widthPixels;
        int height=popUpList.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.9));




        searchBarTextView=(EditText)findViewById(R.id.searchBarTextView);
        topImageView=(ImageView)findViewById(R.id.topImageView);
        bottomImageView=(ImageView)findViewById(R.id.bottomImageView);
        divider=(TextView)findViewById(R.id.divider);
        mixedBreedCheckBox=(CheckBox)findViewById(R.id.mixedBreedCheckBox);
        selectButton=(Button)findViewById(R.id.selectButton);

        final ListView listView = (ListView)findViewById(R.id.listView);
        final CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);
        Intent intent=getIntent();
        user= (User) intent.getSerializableExtra("user");
        dogName=intent.getStringExtra("dogName");
        dogPhoto=intent.getData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                position=i;

                    for(int j=0;j< adapterView.getChildCount();j++)
                    {
                        if (position == j) {
                            listView.getChildAt(j).setBackgroundColor(Color.DKGRAY);
                            Toast.makeText(getApplicationContext(), dogBreedNames[j], Toast.LENGTH_LONG).show();
                        }
                        else {
                            listView.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);
                        }
                    }
                Toast.makeText(getApplicationContext(), "Selected", Toast.LENGTH_LONG).show();

            }
        });


        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent selectedBreedIntent=new Intent(getApplicationContext(),SelectBreedActivity.class);
                selectedBreedIntent.putExtra("user",user);
                selectedBreedIntent.putExtra("dogName",dogName);
                selectedBreedIntent.putExtra("Breed",dogBreedNames[position]); //BACK TO PREVIOUS PAGE WITH  SELECTED BREED//

                startActivity(selectedBreedIntent);
            }
        });

        searchBarTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                BreedListActivity.this.customAdapter.getFilter().filter(charSequence);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

        });

    }














    class CustomAdapter extends BaseAdapter implements Filterable {

        @Override
        public int getCount() {
            return dogBreedNames.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view= getLayoutInflater().inflate(R.layout.customlayout,null);

            ImageView imageView=(ImageView)view.findViewById(R.id.imageView);
            TextView textView_name=(TextView)view.findViewById(R.id.textView_name);

            imageView.setImageResource(dogBreedImage[0]);
            /* ***NOTE***
            IF WE WANT TO PRESENT ARRAY OF IMAGES - REPLACE 0 WITH i
            */
            textView_name.setText(dogBreedNames[i]);

            return view;
        }

        @Override
        public Filter getFilter() {
            return null;
        }
    }
}


